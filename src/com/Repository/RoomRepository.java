package com.Repository;

import com.Model.Doctor;
import com.Model.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomRepository {
    private List<Room> rooms;

    public RoomRepository() {
        rooms = new ArrayList<Room>();
        rooms.add(new Room("1"));
        rooms.add(new Room("2"));
        rooms.add(new Room("3"));
        rooms.add(new Room("4"));
    }

    public List<Room> getAllRooms() {
        return rooms;
    }


    /**
     * @param doctor
     * @param shiftTime
     * @return true if doctor was assigned to a room, false otherwise
     */
    public Boolean assignDoctor(Doctor doctor, int shiftTime) {
        for (Room room : this.rooms
        ) {
            if (room.getShiftTimeRemaining() >= shiftTime) {
                room.setDoctor(doctor);
                room.setShiftTimeRemaining(room.getShiftTimeRemaining() - shiftTime);
                return true;
            }
        }
        return false;
    }


}
