import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { resourceService } from '../api/resourceService';
import Spinner from '../components/Spinner';

const ResourceDetail = () => {
  const { id } = useParams();
  const [resource, setResource] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchResource = async () => {
      try {
        const resp = await resourceService.getById(id);
        setResource(resp.data);
      } catch (error) {
        console.error("Failed to fetch resource", error);
      } finally {
        setLoading(false);
      }
    };
    fetchResource();
  }, [id]);

  if (loading) return <Spinner />;
  if (!resource) return <div>Resource not found.</div>;

  return (
    <div className="px-4 py-5 sm:px-6 bg-white shadow sm:rounded-lg">
      <h3 className="text-lg leading-6 font-medium text-gray-900">{resource.name}</h3>
      <p className="mt-1 max-w-2xl text-sm text-gray-500">{resource.type}</p>
      <div className="mt-5 border-t border-gray-200">
        <dl className="sm:divide-y sm:divide-gray-200">
          <div className="py-4 sm:py-5 sm:grid sm:grid-cols-3 sm:gap-4 flex flex-col">
            <dt className="text-sm font-medium text-gray-500">Location</dt>
            <dd className="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">{resource.location}</dd>
          </div>
          <div className="py-4 sm:py-5 sm:grid sm:grid-cols-3 sm:gap-4 flex flex-col">
            <dt className="text-sm font-medium text-gray-500">Capacity</dt>
            <dd className="mt-1 text-sm text-gray-900 sm:mt-0 sm:col-span-2">{resource.capacity} people</dd>
          </div>
        </dl>
      </div>
    </div>
  );
};

export default ResourceDetail;
