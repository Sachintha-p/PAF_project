import React, { useState, useEffect } from 'react';
import { notificationService } from '../api/notificationService';
import Spinner from '../components/Spinner';

const Notifications = () => {
  const [notifications, setNotifications] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchNotifications = async () => {
      try {
        const resp = await notificationService.getMyNotifications();
        setNotifications(resp.data || []);
      } catch (error) {
        console.error("Failed to fetch notifications", error);
      } finally {
        setLoading(false);
      }
    };
    fetchNotifications();
  }, []);

  const handleMarkAsRead = async (id) => {
    try {
      await notificationService.markAsRead(id);
      setNotifications(notifications.map(n => n.id === id ? {...n, isRead: true} : n));
    } catch (error) {}
  };

  const handleMarkAllAsRead = async () => {
    try {
      await notificationService.markAllAsRead();
      setNotifications(notifications.map(n => ({...n, isRead: true})));
    } catch (error) {}
  };

  if (loading) return <Spinner />;

  return (
    <div className="px-4 py-5 sm:px-6">
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-2xl font-bold text-gray-900">Notifications</h1>
        <button onClick={handleMarkAllAsRead} className="text-sm text-blue-600 hover:text-blue-800">
          Mark all as read
        </button>
      </div>
      
      <div className="bg-white shadow overflow-hidden sm:rounded-md">
        <ul className="divide-y divide-gray-200">
          {notifications.length === 0 ? (
            <li className="px-6 py-4 text-center text-sm text-gray-500">No notifications.</li>
          ) : (
            notifications.map((notification) => (
              <li key={notification.id}>
                <div className={`px-4 py-4 sm:px-6 ${!notification.isRead ? 'bg-blue-50' : ''}`}>
                  <div className="flex items-center justify-between">
                    <p className="text-sm font-medium text-blue-600 truncate">{notification.type}</p>
                    <div className="ml-2 flex-shrink-0 flex">
                      {!notification.isRead && (
                        <button onClick={() => handleMarkAsRead(notification.id)} className="text-xs text-gray-500 underline">
                          Mark as read
                        </button>
                      )}
                    </div>
                  </div>
                  <div className="mt-2 sm:flex sm:justify-between">
                    <div className="sm:flex">
                      <p className="flex items-center text-sm text-gray-500">
                        {notification.message}
                      </p>
                    </div>
                  </div>
                </div>
              </li>
            ))
          )}
        </ul>
      </div>
    </div>
  );
};

export default Notifications;
