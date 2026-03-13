import React, { useState, useEffect } from 'react';
import { bookingService } from '../api/bookingService';
import Table from '../components/Table';
import Badge from '../components/Badge';
import Spinner from '../components/Spinner';

const Bookings = () => {
  const [bookings, setBookings] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
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
    fetchBookings();
  }, []);

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
    { header: 'Date', accessor: 'date' },
    { header: 'Time', render: (b) => `${b.startTime} - ${b.endTime}` },
    { 
      header: 'Status', 
      render: (b) => <Badge color={getStatusColor(b.status)}>{b.status}</Badge> 
    }
  ];

  if (loading) return <Spinner />;

  return (
    <div className="px-4 py-5 sm:px-6">
      <h1 className="text-2xl font-bold text-gray-900 mb-6">My Bookings</h1>
      <Table columns={columns} data={bookings} />
    </div>
  );
};

export default Bookings;
