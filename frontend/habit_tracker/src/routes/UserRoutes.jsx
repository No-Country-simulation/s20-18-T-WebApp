import React from "react";
import { Routes, Route } from "react-router-dom";
import Habits from "../pages/Habits/Habits";
import Home from "../pages/Home/Home";
import {Profile} from "../pages/Profile/Profile";
import {Settings} from "../pages/Settings/Settings";

const UserRoutes = () => {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/habits" element={<Habits />} />
      <Route path="/profile" element={<Profile />} />
      <Route path="/settings" element={<Settings />} />
    </Routes>
  );
};

export default UserRoutes;
