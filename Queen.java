package com.company;

public class Queen {

    private int x, y;

    public Queen(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    @Override
    public boolean equals(Object obj)
    {
        Queen queen = (Queen)obj;
        return (this.x == queen.getX() && this.y == queen.getY());
    }
}
