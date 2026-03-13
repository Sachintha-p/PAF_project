import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { bookingService } from '../api/bookingService';
import Badge from '../components/Badge';
import Spinner from '../components/Spinner';

const BookingDetail = () => {
  const { id } = useParams();
  const [booking, setBooking] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchBooking = async () => {
      try {
        const resp = await bookingService.getById(id);
        setBooking(resp.data);
      } catch (error) {
        console.error("Failed to fetch booking", error);
      } finally {
        setLoading(false);
      }
    };
    fetchBooking();
  }, [id]);

  if (loading) return <Spinner />;
  if (!booking) return <div>Booking not found.</div>;

  const getStatusColor = (status) => {
    switch(status) {
      case 'APPROVED': return 'green';
      case 'PENDING': return 'yellow';
      case 'REJECTED': return 'red';
      case 'CANCELLED': return 'gray';
      default: return 'gray';
    }
  };

  return (
    <div className="px-4 py-5 sm:px-6 bg-white shadow sm:rounded-lg">
      <div className="flex justify-between items-center mb-4">
        <h3 className="text-lg leading-6 font-medium text-gray-900">Booking: {booking.resourceName}</h3>
        <Badge color={getStatusColor(booking.status)}>{booking.status}</Badge>
      </div>
      <div className="mt-5 border-t border-gray-200">
        <dl className="sm:divide-y sm:divide-gray-200">
          <div className="py-4 sm:py-5 sm:grid sm:grid-cols-3 sm:gap-4 flex flex-col">
            <dt className="text-sm font-medium text-gray-500">Date & Time</dt>
            <dd className="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">{booking.date} from {booking.startTime} to {booking.endTime}</dd>
          </div>
          <div className="py-4 sm:py-5 sm:grid sm:grid-cols-3 sm:gap-4 flex flex-col">
            <dt className="text-sm font-medium text-gray-500">Purpose</dt>
            <dd className="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">{booking.purpose}</dd>
          </div>
        </dl>
      </div>
    </div>
  );
};

export default BookingDetail;
