import React from "react";
import { Routes, Route } from "react-router-dom";

import {Home, Habits, ViewHabit, Archives, Profile, AboutUs, AboutHaru} from '../pages';

const UserRoutes = () => {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/habits" element={<Habits />} />
      <Route path="/profile" element={<Profile />} />
      <Route path="/archives" element={<Archives />} />
      <Route path="/viewhabit/:id" element={<ViewHabit />} />
      <Route path="/us" element={<AboutUs />} />
      <Route path="/haru" element={<AboutHaru />} />      
    </Routes>
  );
};

export default UserRoutes;



