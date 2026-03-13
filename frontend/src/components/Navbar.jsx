import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import { Bell } from 'lucide-react';

const Navbar = () => {
  const { user, isAdmin } = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    // In a real app, clear token/session and redirect
    // For scaffold, just redirecting to login to simulate
    window.location.href = '/login';
  };

  if (!user) return null;

  return (
    <nav className="bg-white shadow">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex justify-between h-16">
          <div className="flex">
            <div className="flex-shrink-0 flex items-center">
              <Link to="/dashboard" className="text-xl font-bold text-blue-600">Smart Campus</Link>
            </div>
            <div className="hidden sm:ml-6 sm:flex sm:space-x-8">
              <Link to="/dashboard" className="border-transparent text-gray-500 hover:border-gray-300 hover:text-gray-700 inline-flex items-center px-1 pt-1 border-b-2 text-sm font-medium">Dashboard</Link>
              <Link to="/resources" className="border-transparent text-gray-500 hover:border-gray-300 hover:text-gray-700 inline-flex items-center px-1 pt-1 border-b-2 text-sm font-medium">Resources</Link>
              <Link to="/bookings" className="border-transparent text-gray-500 hover:border-gray-300 hover:text-gray-700 inline-flex items-center px-1 pt-1 border-b-2 text-sm font-medium">My Bookings</Link>
              <Link to="/tickets" className="border-transparent text-gray-500 hover:border-gray-300 hover:text-gray-700 inline-flex items-center px-1 pt-1 border-b-2 text-sm font-medium">Tickets</Link>
              
              {isAdmin && (
                <div className="flex items-center space-x-4 ml-4 pl-4 border-l border-gray-200">
                  <span className="text-xs font-semibold text-gray-400 uppercase tracking-wider">Admin</span>
                  <Link to="/admin/bookings" className="text-gray-500 hover:text-gray-700 text-sm font-medium">Bookings</Link>
                  <Link to="/admin/tickets" className="text-gray-500 hover:text-gray-700 text-sm font-medium">Tickets</Link>
                  <Link to="/admin/users" className="text-gray-500 hover:text-gray-700 text-sm font-medium">Users</Link>
                </div>
              )}
            </div>
          </div>
          <div className="hidden sm:ml-6 sm:flex sm:items-center space-x-4">
            <Link to="/notifications" className="p-1 rounded-full text-gray-400 hover:text-gray-500 focus:outline-none relative">
              <span className="sr-only">View notifications</span>
              <Bell className="h-6 w-6" aria-hidden="true" />
              <span className="absolute top-0 right-0 block h-2 w-2 rounded-full bg-red-400 ring-2 ring-white" />
            </Link>

            <div className="ml-3 relative flex items-center space-x-3">
              <Link to="/profile" className="flex text-sm border-2 border-transparent rounded-full focus:outline-none focus:border-gray-300 transition">
                {user.profilePicture ? (
                  <img className="h-8 w-8 rounded-full" src={user.profilePicture} alt="" />
                ) : (
                  <span className="h-8 w-8 rounded-full bg-gray-200 flex items-center justify-center text-gray-500">{user.name.charAt(0)}</span>
                )}
              </Link>
              <button onClick={handleLogout} className="text-sm font-medium text-gray-500 hover:text-gray-700">Logout</button>
            </div>
          </div>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
