// CSE 142 Homework 8 (Critters)
// Authors: Stuart Reges and Marty Stepp
//
// Class CritterModel keeps track of the state of the critter simulation.

import java.util.*;
import java.awt.Point;
import java.awt.Color;
import java.lang.reflect.*;

public class CritterModel {
    private int height;
    private int width;
    private Critter[][] grid;
    private Map<Critter, PrivateData> info;
    private SortedMap<String, Integer>critterCount;
    private boolean debugView;
    private int simulationCount;
    private static boolean created;
    
    public CritterModel(int width, int height) {
        // this prevents someone from trying to create their own copy of
        // the GUI components
        if (created)
            throw new RuntimeException("Only one world allowed");
        created = true;

        this.width = width;
        this.height = height;
        grid = new Critter[width][height];
        info = new HashMap<Critter, PrivateData>();
        critterCount = new TreeMap<String, Integer>();
        this.debugView = false;
    }

    public Iterator<Critter> iterator() {
        return info.keySet().iterator();
    }

    public Point getPoint(Critter c) {
        return info.get(c).p;
    }

    public Color getColor(Critter c) {
        return info.get(c).color;
    }

    public String getString(Critter c) {
        return info.get(c).string;
    }

    public void add(int number, Class<? extends Critter> critter) {
        Random r = new Random();
        Critter.Direction[] directions = Critter.Direction.values();
        if (info.size() + number > width * height)
            throw new RuntimeException("adding too many critters");
        for (int i = 0; i < number; i++) {
            Critter next;
            try {
                next = makeCritter(critter);
            } catch (Exception e) {
                System.out.println("ERROR: " + critter + " does not have" +
                                   " the appropriate constructor.");
                System.exit(1);
                return;
            }
            int x, y;
            do {
                x = r.nextInt(width);
                y = r.nextInt(height);
            } while (grid[x][y] != null);
            grid[x][y] = next;
            
            Critter.Direction d = directions[r.nextInt(directions.length)];
            info.put(next, new PrivateData(new Point(x, y), d, 0,
                                           next.getColor(), next.toString()));
        }
        String name = critter.getName();
        if (!critterCount.containsKey(name))
            critterCount.put(name, number);
        else
            critterCount.put(name, critterCount.get(name) + number);
    }

    @SuppressWarnings("unchecked")
    private Critter makeCritter(Class critter) throws Exception {
        Constructor c = critter.getConstructors()[0];
        if (critter.toString().equals("class Bear")) {
            // flip a coin
            boolean b = Math.random() < 0.5;
            return (Critter) c.newInstance(new Object[] {b});
        } else {
            return (Critter) c.newInstance();
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getAppearance(Critter c) {
        // Override specified toString if debug flag is true
        if (!debugView) 
            return info.get(c).string;
        else {
            PrivateData data = info.get(c);
            if (data.direction == Critter.Direction.NORTH) return "^";
            else if (data.direction == Critter.Direction.SOUTH) return "v";
            else if (data.direction == Critter.Direction.EAST) return ">";
            else return "<";
        }
    }
    
    public void toggleDebug() {
        this.debugView = !this.debugView;
    }

    private boolean inBounds(int x, int y) {
        return (x >= 0 && x < width && y >= 0 && y < height);
    }

    private boolean inBounds(Point p) {
        return inBounds(p.x, p.y);
    }

    // returns the result of rotating the given direction clockwise
    private Critter.Direction rotate(Critter.Direction d) {
        if (d == Critter.Direction.NORTH) return Critter.Direction.EAST;
        else if (d == Critter.Direction.SOUTH) return Critter.Direction.WEST;
        else if (d == Critter.Direction.EAST) return Critter.Direction.SOUTH;
        else return Critter.Direction.NORTH;
    }

    private Point pointAt(Point p, Critter.Direction d) {
        if (d == Critter.Direction.NORTH) return new Point(p.x, p.y - 1);
        else if (d == Critter.Direction.SOUTH) return new Point(p.x, p.y + 1);
        else if (d == Critter.Direction.EAST) return new Point(p.x + 1, p.y);
        else return new Point(p.x - 1, p.y);
    }

    private Info getInfo(PrivateData data, Class original) {
        Critter.Neighbor[] neighbors = new Critter.Neighbor[4];
        Critter.Direction d = data.direction;
        for (int i = 0; i < 4; i++) {
            neighbors[i] = getStatus(pointAt(data.p, d), original);
            d = rotate(d);
        }
        return new Info(neighbors, data.direction, data.infectCount);
    }

    private Critter.Neighbor getStatus(Point p, Class original) {
        if (!inBounds(p))
            return Critter.Neighbor.WALL;
        else if (grid[p.x][p.y] == null)
            return Critter.Neighbor.EMPTY;
        else if (grid[p.x][p.y].getClass() == original)
            return Critter.Neighbor.SAME;
        else
            return Critter.Neighbor.OTHER;
    }

    @SuppressWarnings("unchecked")
    public void update() {
        simulationCount++;
        Object[] list = info.keySet().toArray();
        Collections.shuffle(Arrays.asList(list));
        Arrays.sort(list, new Comparator() {
                public int compare(Object x, Object y) {
                    return Math.min(10, info.get(x).infectCount) -
                        Math.min(10, info.get(y).infectCount);
                }
            });
        for (int i = 0; i < list.length; i++) {
            Critter next = (Critter)list[i];
            PrivateData data = info.get(next);
            if (data == null) {
                // happens when creature was infected earlier in this round
                continue;
            }
            Point p = data.p;
            Point p2 = pointAt(p, data.direction);
            Critter.Action move = next.getMove(getInfo(data, next.getClass()));
            data.color = next.getColor();
            data.string = next.toString();
            if (move == Critter.Action.LEFT)
                data.direction = rotate(rotate(rotate(data.direction)));
            else if (move == Critter.Action.RIGHT)
                data.direction = rotate(data.direction);
            else if (move == Critter.Action.HOP) {
                if (inBounds(p2) && grid[p2.x][p2.y] == null) {
                    grid[p2.x][p2.y] = grid[p.x][p.y];
                    grid[p.x][p.y] = null;
                    data.p = p2;
                }
            } else if (move == Critter.Action.INFECT) {
                if (inBounds(p2) && grid[p2.x][p2.y] != null && grid[p2.x][p2.y].getClass() != next.getClass()) {
                    Critter other = grid[p2.x][p2.y];
                    // remember the old critter's private data
                    PrivateData oldData = info.get(other);
                    // then remove that old critter
                    String c1 = other.getClass().getName();
                    critterCount.put(c1, critterCount.get(c1) - 1);
                    String c2 = next.getClass().getName();
                    critterCount.put(c2, critterCount.get(c2) + 1);
                    info.remove(other);
                    // and add a new one to the grid
                    try {
                        grid[p2.x][p2.y] = makeCritter(next.getClass());
                    } catch (Exception e) {
                        throw new RuntimeException("" + e);
                    }
                    // and add to the map
                    info.put(grid[p2.x][p2.y], oldData);
                    // and remember that we infected a critter
                    data.infectCount++;
                }
            }
        }
    }

    public Set<Map.Entry<String, Integer>> getCounts() {
        return Collections.unmodifiableSet(critterCount.entrySet());
    }

    public int getSimulationCount() {
        return simulationCount;
    }

    private class PrivateData {
        public Point p;
        public Critter.Direction direction;
        public int infectCount;
        public Color color;
        public String string;

        public PrivateData(Point p, Critter.Direction d, int infectCount,
                           Color color, String string) {
            this.p = p;
            this.direction = d;
            this.infectCount = infectCount;
            this.color = color;
            this.string = string;
        }

        public String toString() {
            return p + " " + direction + " " + infectCount;
        }
    }

    // an object used to query a critter's state (neighbors, direction)
    private static class Info implements CritterInfo {
        private Critter.Neighbor[] neighbors;
        private Critter.Direction direction;
        private int infectCount;

        public Info(Critter.Neighbor[] neighbors, Critter.Direction d,
                    int infectCount) {
            this.neighbors = neighbors;
            this.direction = d;
            this.infectCount = infectCount;
        }

        public Critter.Neighbor getFront() {
            return neighbors[0];
        }

        public Critter.Neighbor getBack() {
            return neighbors[2];
        }

        public Critter.Neighbor getLeft() {
            return neighbors[3];
        }

        public Critter.Neighbor getRight() {
            return neighbors[1];
        }

        public Critter.Direction getDirection() {
            return direction;
        }

        public int getInfectCount() {
            return infectCount;
        }
    }
}
