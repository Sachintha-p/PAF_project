import React, { useState, useEffect } from 'react';
import { bookingService } from '../../api/bookingService';
import Table from '../../components/Table';
import Badge from '../../components/Badge';
import Button from '../../components/Button';
import Spinner from '../../components/Spinner';

const AdminBookings = () => {
  const [bookings, setBookings] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchBookings();
  }, []);

  const fetchBookings = async () => {
    try {
      const resp = await bookingService.getAll();
      setBookings(resp.data || []);
    } catch (error) {
      console.error("Failed to fetch bookings", error);
    } finally {
      setLoading(false);
    }
  };

  const handleApprove = async (id) => {
    try {
      await bookingService.approve(id);
      fetchBookings(); // Refresh list
    } catch (error) {}
  };

  const handleReject = async (id) => {
    try {
      const reason = prompt("Enter rejection reason:");
      if (reason) {
        await bookingService.reject(id, reason);
        fetchBookings();
      }
    } catch (error) {}
  };

  const getStatusColor = (status) => {
    switch(status) {
      case 'APPROVED': return 'green';
      case 'PENDING': return 'yellow';
      case 'REJECTED': return 'red';
      case 'CANCELLED': return 'gray';
      default: return 'gray';
    }
  };

  const columns = [
    { header: 'Resource', accessor: 'resourceName' },
    { header: 'User', accessor: 'userName' },
    { header: 'Date', accessor: 'date' },
    { header: 'Time', render: (b) => `${b.startTime} - ${b.endTime}` },
    { 
      header: 'Status', 
      render: (b) => <Badge color={getStatusColor(b.status)}>{b.status}</Badge> 
    },
    {
      header: 'Actions',
      render: (b) => b.status === 'PENDING' ? (
        <div className="flex space-x-2">
          <Button size="sm" onClick={() => handleApprove(b.id)}>Approve</Button>
          <Button size="sm" variant="danger" onClick={() => handleReject(b.id)}>Reject</Button>
        </div>
      ) : null
    }
  ];

  if (loading) return <Spinner />;

  return (
    <div className="px-4 py-5 sm:px-6">
      <h1 className="text-2xl font-bold text-gray-900 mb-6">Manage Bookings</h1>
      <Table columns={columns} data={bookings} />
    </div>
  );
};

export default AdminBookings;
