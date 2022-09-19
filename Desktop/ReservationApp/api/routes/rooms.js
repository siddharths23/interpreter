import express from "express";
import {
  createRoom,
  deleteRoom,
  getallRoom,
  getRoom,
  updateRoom,
} from "./controllers/room.js";

const router = express.Router();
router.post("/:hotelid", createRoom);
//UPDATE
router.put("/:id", updateRoom);
//DELETE
router.delete("/:id", deleteRoom);
//GET
router.get("/:id", getRoom);
//GET ALL HOTELS
router.get("/", getallRoom);
export default router;
