package org.example.Board;

public class Coordinate {
    private final Integer row;
    private final Integer column;
    private char charachter;

    //TESTER
    public static void main(String[] Args){
        Coordinate c1 = new Coordinate(3,6);
        System.out.println(c1);
        System.out.println(new Coordinate('h',3));
    }

    //CONSTRUCTOR
    public Coordinate(int c, int r){
        assert (r>=1 && r<=8);
        row=r;
        assert (c>=1 & c<=8);
        column=c;
        setCharachter((char) ('a'-1+c));
    }
    public Coordinate (char c, int r){
        row=r;
        setCharachter(c);
        column = (int) (getCharachter() -'a'+1);
    }

    //METHODS
    public String toString(){
        return getCharachter() + getRow().toString();
    }

    /**
     *
     * @param other the other coordinate
     * @return true if row and columns are the same
     */
    public boolean equals(Coordinate other){
        return (getRow().equals(other.getColumn())&& getColumn().equals(other.getColumn()));
    }

    public Integer getRow() {
        return row;
    }

    public Integer getColumn() {
        return column;
    }

    public char getCharColumn(){ return (char) ('a'+column-1); }

    public char getCharachter() {
        return charachter;
    }

    public void setCharachter(char ch) {
        this.charachter = ch;
    }

    boolean rowIsvalid(){
        return row<9 && row>0;
    }
    boolean columnIsValid(){
        return column<9 && column>0;
    }

    boolean isValid(){
        return rowIsvalid() && columnIsValid();
    }

}
