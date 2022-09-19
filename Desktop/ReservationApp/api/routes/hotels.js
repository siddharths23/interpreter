import express from "express";
import Hotel from "./models/Hotel.js";

import {
  createHotel,
  deleteHotel,
  getallHotel,
  getHotel,
  updateHotel,
  countByCity,
  countByType,
} from "./controllers/hotel.js";

const router = express.Router();

//CREATE

router.post("/", createHotel);
//UPDATE
router.put("/:id", updateHotel);
//DELETE
router.delete("/:id", deleteHotel);
//GET
router.get("/find/:id", getHotel);
//GET ALL HOTELS
router.get("/", getallHotel);
router.get("/countByCity", countByCity);
router.get("/countByType", countByType);

export default router;
