import jwt from "jsonwebtoken";
import { createError } from "./err.js";

export const verifyToken = (req, res, next) => {
  const token = req.cookies.access_token;
  if (!token) {
    return createError(401, "not admin");
  }
  jwt.verify(process.env.JWT, token, (err, user) => {
    if (err) {
      return createError(402, "token is invalid");
    }
    req.user = user;
    next();
  });
};
