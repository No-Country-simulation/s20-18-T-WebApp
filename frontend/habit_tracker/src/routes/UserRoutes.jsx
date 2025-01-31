import React from "react";
import { Routes, Route } from "react-router-dom";
import Home from "../pages/Home/Home";
import Habits from "../pages/Habits/Habits";
import { ViewHabit } from "../pages/ViewHabit/ViewHabit";
import Archives from '../pages/Archives/Archives';
import {Profile} from "../pages/Profile/Profile";
import {Settings} from "../pages/Settings/Settings";

const UserRoutes = () => {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/habits" element={<Habits />} />
      <Route path="/profile" element={<Profile />} />
      <Route path="/archives" element={<Archives />} />
      <Route path="/viewhabit/:id" element={<ViewHabit />} />
      <Route path="/settings" element={<Settings />} />
    </Routes>
  );
};

export default UserRoutes;
