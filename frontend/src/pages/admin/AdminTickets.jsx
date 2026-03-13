import React, { useState, useEffect } from 'react';
import { ticketService } from '../../api/ticketService';
import Table from '../../components/Table';
import Badge from '../../components/Badge';
import Spinner from '../../components/Spinner';

const AdminTickets = () => {
  const [tickets, setTickets] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchTickets();
  }, []);

  const fetchTickets = async () => {
    try {
      const resp = await ticketService.getAll();
      setTickets(resp.data || []);
    } catch (error) {
      console.error("Failed to fetch tickets", error);
    } finally {
      setLoading(false);
    }
  };

  const getStatusColor = (status) => {
    switch(status) {
      case 'OPEN': return 'blue';
      case 'IN_PROGRESS': return 'orange';
      case 'RESOLVED': return 'green';
      case 'CLOSED': return 'gray';
      case 'REJECTED': return 'red';
      default: return 'gray';
    }
  };

  const columns = [
    { header: 'Resource', accessor: 'resourceName' },
    { header: 'Reported By', accessor: 'reportedByName' },
    { header: 'Category', accessor: 'category' },
    { header: 'Priority', accessor: 'priority' },
    { 
      header: 'Status', 
      render: (t) => <Badge color={getStatusColor(t.status)}>{t.status}</Badge> 
    },
    {
      header: 'Assigned To',
      accessor: 'assignedToName'
    }
  ];

  if (loading) return <Spinner />;

  return (
    <div className="px-4 py-5 sm:px-6">
      <h1 className="text-2xl font-bold text-gray-900 mb-6">Manage Tickets</h1>
      <Table columns={columns} data={tickets} />
    </div>
  );
};

export default AdminTickets;
