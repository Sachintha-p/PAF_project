import React, { useState, useEffect } from 'react';
import { resourceService } from '../api/resourceService';
import Table from '../components/Table';
import Badge from '../components/Badge';
import Spinner from '../components/Spinner';

const Resources = () => {
  const [resources, setResources] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchResources();
  }, []);

  const fetchResources = async () => {
    try {
      const resp = await resourceService.getAll();
      setResources(resp.data || []);
    } catch (error) {
      console.error("Failed to fetch resources", error);
    } finally {
      setLoading(false);
    }
  };

  const columns = [
    { header: 'Name', accessor: 'name' },
    { header: 'Type', accessor: 'type' },
    { header: 'Capacity', accessor: 'capacity' },
    { header: 'Location', accessor: 'location' },
    { 
      header: 'Status', 
      accessor: 'status',
      render: (item) => (
        <Badge color={item.status === 'ACTIVE' ? 'green' : 'red'}>
          {item.status}
        </Badge>
      )
    }
  ];

  if (loading) return <Spinner />;

  return (
    <div className="px-4 py-5 sm:px-6">
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-2xl font-bold text-gray-900">Resource Catalogue</h1>
      </div>
      <Table columns={columns} data={resources} />
    </div>
  );
};

export default Resources;
