public class boardspot {
    private int x;
    private int y;

    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }

    public boardspot(int lx, int ly, boolean b){
        int x = lx;
        int y = ly;
        boolean notAvailable = b;
    }

}
