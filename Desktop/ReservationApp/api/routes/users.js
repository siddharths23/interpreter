import express from "express";
import {
  updateUser,
  deleteUser,
  getUser,
  getallUser,
} from "./controllers/user.js";
import { verifyToken } from "./utils/verifytoken.js";
const router = express.Router();
router.get("/checkauth", verifyToken, (req, res, next) => {
  res.send("hello user, you are authenticated");
});
router.put("/:id", updateUser);
//DELETE
router.delete("/:id", deleteUser);
//GET
router.get("/:id", getUser);
//GET ALL UserS
router.get("/", getallUser);

export default router;
