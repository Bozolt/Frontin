package game.utilities.variables.grids;

public class SuperBooleanFlatRowGrid implements Grid <Boolean> {

    private final int[][] grid;
    private int width, height;
    private boolean baseValue;

    public SuperBooleanFlatRowGrid(int width, int height, Boolean baseValue) {
        if (width <= 0 || height <= 0) throw new IllegalArgumentException("Dimensions a Grid cannot be 0 or negative");
        if (baseValue == null) throw new IllegalArgumentException("Value cannot be null");
        this.grid = new int[(width-1)/32+1][height];

        this.width = width;
        this.height = height;
        this.baseValue = baseValue;
    }

    @Override
    public void setArea(int x, int y, int width, int height, Boolean value) {
        if (x < 0 || y < 0 || x >= this.width || y >= this.height) throw new IllegalArgumentException("Starting position out of bounds");
        if (x+(width-1l) >= this.width || y+(height-1l) >= this.height) throw new IllegalArgumentException("Ending position out of bounds");
        if (width <= 0 || height <= 0) throw new IllegalArgumentException("Area cannot have 0 or negative dimension(s)");
        if (value == null) throw new IllegalArgumentException("Value cannot be null");

        if (width == 1) {
            for (int Y = y; Y < y+height; Y ++) {
                if (baseValue == value) {this.grid[x/32][Y] = ~(~this.grid[x/32][Y] | (Integer.MIN_VALUE >>> (x%32)));}
                else {this.grid[x/32][Y] = this.grid[x/32][Y] | (Integer.MIN_VALUE >>> (x%32));}
            }
        }
        else if (x/32 == (x+width-1)/32) {
            for (int Y = y; Y < y+height; Y ++) {
                if (baseValue == value) {this.grid[x/32][Y] = ~(~this.grid[x/32][Y] | (-1 >>> (32-width-x) << (32-width-x) << (x%32) >>> (x%32)));}
                else {this.grid[x/32][Y] = this.grid[x/32][Y] | (-1 >>> (32-width-x) << (32-width-x) << (x%32) >>> (x%32));}
            }
        }
        else {
            for (int Y = y; Y < y+height; Y ++) {
                if (value == baseValue) {
                    this.grid[x/32][Y] = ~(~this.grid[x/32][Y] | (-1 >>> (x%32)));

                    this.grid[(x+width-1)/32][Y] = ~(~this.grid[(x+width-1)/32][Y] | (-1 << (32-(x+width)%32)));
                }
                else {
                    this.grid[x/32][Y] = this.grid[x/32][Y] | (-1 >>> (x%32));

                    this.grid[(x+width-1)/32][Y] = this.grid[(x+width-1)/32][Y] | (-1 << (32-(x+width)%32));
                }
            }

            for (int X = x/32+1; X < (x+width-1)/32; X ++) {
                for (int Y = y; Y < y+height; Y ++) {
                    if (value == baseValue) {
                        this.grid[X][Y] = 0;
                    }
                    else {
                        this.grid[X][Y] = -1;
                    }
                }
            }
        }
    }

    @Override
    public Boolean getValue(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) throw new IllegalArgumentException("Position out of bounds");
        return (this.grid[x/32][y] << x%32 & Integer.MIN_VALUE) == Integer.MIN_VALUE ? !baseValue : baseValue;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

   
    
}
