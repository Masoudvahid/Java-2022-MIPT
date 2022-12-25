package org.example;

import java.util.Comparator;
import java.util.Scanner;  // Import the Scanner class
import java.util.ArrayList;


public class A_City_traffic {

    static public int M;
    static public int N;


    public static void main(String[] args) throws RoadAccident {
        Scanner scanner = new Scanner(System.in);

        // Input dimensions the map and return M * N map
        int[][] map = ParseMapInput(scanner.nextLine());

        // Input buildings and occupy in the map
        ParseBuildingInput(scanner.nextLine(), map);
        ArrayList<Vehicle> vehicles = ParseVehicleInput(scanner.nextLine(), map);

        ArrayList<Coordinates> Moves = ParseMovesInput(scanner.nextLine());
        for (Coordinates m : Moves) {
            int car_index = map[m.x_begin][m.y_begin] - 1;

            try {
                if (AvailableMove(m, map) && InsideBoundaries(m) && VehiclesTooClose(m, map) && EnoughFuel(vehicles.get(car_index))) {
                    map[m.x_begin][m.y_begin] = 0;
                    map[m.x_end][m.y_end] = car_index + 1;
                    vehicles.get(car_index).fuel_ -= vehicles.get(car_index).type + 1;
                    vehicles.get(car_index).x = m.x_end;
                    vehicles.get(car_index).y = m.y_end;
                }
            } catch (RoadAccident o) {
                System.out.println("road accident");
                return;
            } catch (OutOfBound o) {
                System.out.println("out of bound");
                return;
            } catch (OutOfFuel o) {
                System.out.println("out of fuel");
                return;
            } catch (VehiclesTooClose o) {
                System.out.println("vehicles are too close to each other");
                return;
            } catch (Exception e) {
                System.out.println("error");
                return;
            }
        }

        vehicles.stream().max(Comparator.comparingInt(Vehicle::GetFuel)).ifPresent(result -> System.out.println(result.type + " " + (result.x + 1) + " " + (result.y + 1)));
    }

    public static class RoadAccident extends RuntimeException {
    }

    public static Boolean AvailableMove(Coordinates move, int[][] map) throws RoadAccident {
        if (map[move.x_end][move.y_end] < 0) {
            throw new RoadAccident();
        }
        return true;
    }

    public static class OutOfBound extends RuntimeException {
    }

    public static Boolean InsideBoundaries(Coordinates move) throws OutOfBound {
        if (move.x_end >= 0 && move.y_end >= 0 &&
                move.x_end < M && move.y_end < N) {
            return true;
        }
        throw new OutOfBound();
    }

    public static class VehiclesTooClose extends RuntimeException {
    }

    public static Boolean VehiclesTooClose(Coordinates move, int[][] map) throws VehiclesTooClose {
        if (map[move.x_end][move.y_end] > 0) {
            throw new VehiclesTooClose();
        }
        return true;
    }

    public static class OutOfFuel extends RuntimeException {
    }

    public static Boolean EnoughFuel(Vehicle vehicle) throws OutOfFuel {
        if (vehicle.fuel_ != 0) {
            return true;
        }
        throw new OutOfFuel();
    }


    static int[][] ParseMapInput(String input) {
        String[] dimensions = input.split(" ");
        M = Integer.parseInt(dimensions[0]);
        N = Integer.parseInt(dimensions[1]);

        return new int[M][N];
    }

    static class Building {
        int x_1;
        int y_1;
        int x_2;
        int y_2;

        public Building(int x_1, int y_1, int x_2, int y_2) {
            this.x_1 = x_1;
            this.y_1 = y_1;
            this.x_2 = x_2;
            this.y_2 = y_2;
        }
    }

    /**
     * ParseBuildingInput and fill the map with given coordinates of buildings
     *
     * @param input string input form user containing building coordinates
     * @param map   input the map for indicating the building on it
     */
    static void ParseBuildingInput(String input, int[][] map) {
        String[] locations = input.split(" ");

        ArrayList<Building> buildings = new ArrayList<>();
        for (int i = 0; i < locations.length; i += 4) {
            int x_1 = Integer.parseInt(locations[i]) - 1;
            int y_1 = Integer.parseInt(locations[i + 1]) - 1;
            int x_2 = Integer.parseInt(locations[i + 2]) - 1;
            int y_2 = Integer.parseInt(locations[i + 3]) - 1;
            Building b = new Building(x_1, y_1, x_2, y_2);
            buildings.add(b);
        }

        for (Building b : buildings) {
            for (int i = b.x_1; i <= b.x_2; i++) {
                map[i][b.y_1] = -1;
                map[i][b.y_2] = -1;
            }
            for (int i = b.y_1; i <= b.y_2; i++) {
                map[b.x_1][i] = -1;
                map[b.x_2][i] = -1;
            }
        }
    }

    /**
     * The class for storing vehicle information
     */
    static class Vehicle {
        int type;
        int x;
        int y;
        int fuel_;

        public int GetFuel() {
            return this.fuel_;
        }

        public Vehicle(int type, int x, int y, int fuel) {
            this.type = type;
            this.x = x;
            this.y = y;
            this.fuel_ = fuel;
        }
    }

    /**
     * This function get's the input of the vehicles locations
     * as string and stores the index of the vehicle
     * on the map from 0 to the number of vehicles
     * the map with index 0 is the available for moves;
     * the map with index -1 is blocked by surroundings
     * and the map with any other number is the index of the vehicle
     *
     * @param input the string input from user
     * @param map   the map with buildings located
     * @return array of vehicles while the map is also modified with indices of car positions
     */
    static ArrayList<Vehicle> ParseVehicleInput(String input, int[][] map) {
        String[] in = input.split(" ");

        ArrayList<Vehicle> vehicles = new ArrayList<>();
        for (int i = 0; i < in.length; i += 4) {
            int type = Integer.parseInt(in[i]);
            int init_x = Integer.parseInt(in[i + 1]) - 1;
            int init_y = Integer.parseInt(in[i + 2]) - 1;
            int fuel = Integer.parseInt(in[i + 3]);

            map[init_x][init_y] = i / 4 + 1; // Putting the index of the vehicle on the map

            Vehicle v = new Vehicle(type, init_x, init_y, fuel);
            vehicles.add(v);
        }
        return vehicles;

    }


    /**
     * This class for storing the coordinates of buildings
     */
    static class Coordinates {
        int x_begin;
        int y_begin;
        int x_end;
        int y_end;

        public Coordinates(int x_begin, int y_begin, int x_end, int y_end) {
            this.x_begin = x_begin;
            this.y_begin = y_begin;
            this.x_end = x_end;
            this.y_end = y_end;
        }

    }

    /**
     * This function is used to pare the coordinates
     * and store them in the array of coordinates
     *
     * @param input the input as a string from user
     * @return coordinates
     */
    static ArrayList<Coordinates> ParseMovesInput(String input) {
        String[] in = input.split(" ");

        ArrayList<Coordinates> coordinates = new ArrayList<>();
        for (int i = 0; i < in.length; i += 4) {
            int x_begin = Integer.parseInt(in[i]) - 1;
            int y_begin = Integer.parseInt(in[i + 1]) - 1;
            int x_end = Integer.parseInt(in[i + 2]) - 1;
            int y_end = Integer.parseInt(in[i + 3]) - 1;
            Coordinates c = new Coordinates(x_begin, y_begin, x_end, y_end);
            coordinates.add(c);
        }
        return coordinates;
    }

}