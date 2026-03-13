import React, { useState, useEffect } from 'react';
import { authService } from '../../api/authService';
import Table from '../../components/Table';
import Badge from '../../components/Badge';
import Spinner from '../../components/Spinner';

const AdminUsers = () => {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchUsers();
  }, []);

  const fetchUsers = async () => {
    try {
      const resp = await authService.getAllUsers();
      setUsers(resp.data || []);
    } catch (error) {
      console.error("Failed to fetch users", error);
    } finally {
      setLoading(false);
    }
  };

  const handleRoleChange = async (targetId, newRole) => {
    try {
      await authService.changeRole(targetId, newRole);
      fetchUsers();
    } catch (error) {
      console.error("Failed to change role", error);
    }
  };

  const columns = [
    { header: 'Name', accessor: 'name' },
    { header: 'Email', accessor: 'email' },
    { 
      header: 'Role', 
      render: (u) => (
        <select 
          value={u.role} 
          onChange={(e) => handleRoleChange(u.id, e.target.value)}
          className="mt-1 block w-full pl-3 pr-10 py-2 text-base border-gray-300 focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm rounded-md"
        >
          <option value="USER">USER</option>
          <option value="ADMIN">ADMIN</option>
          <option value="TECHNICIAN">TECHNICIAN</option>
        </select>
      )
    },
    { header: 'Provider', accessor: 'provider' }
  ];

  if (loading) return <Spinner />;

  return (
    <div className="px-4 py-5 sm:px-6">
      <h1 className="text-2xl font-bold text-gray-900 mb-6">Manage Users</h1>
      <Table columns={columns} data={users} />
    </div>
  );
};

export default AdminUsers;
