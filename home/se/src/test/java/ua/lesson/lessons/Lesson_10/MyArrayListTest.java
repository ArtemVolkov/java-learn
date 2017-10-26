package ua.lesson.lessons.Lesson_10;

import org.junit.Test;

import static org.junit.Assert.*;

public class MyArrayListTest {
    @Test
    public void addInPosition() throws Exception {
        MyArrayList<Integer> arrayList=new MyArrayList<Integer>();
        arrayList.add(15);
        arrayList.add(15);
        arrayList.add(14,1);
        assertEquals("ua.lesson.lessons.Lesson_10.MyArrayList: [15], [14], [15], ;", arrayList.toString());
        assertEquals(3, arrayList.size());

        arrayList.add(14,0);
        assertEquals("ua.lesson.lessons.Lesson_10.MyArrayList: [14], [15], [14], [15], ;", arrayList.toString());
        assertEquals(4, arrayList.size());

        arrayList.add(14, 4);
        assertEquals("ua.lesson.lessons.Lesson_10.MyArrayList: [14], [15], [14], [15], [14], ;", arrayList.toString());
        assertEquals(5, arrayList.size());
    }

    @Test (expected= IllegalArgumentException.class)
    public void addInPositionWithError() throws Exception {
        MyArrayList<Integer> arrayList=new MyArrayList<Integer>();
        arrayList.add(15);
        arrayList.add(15,2);

    }

    @Test
    public void add() throws Exception {
        MyArrayList<Integer> arrayList=new MyArrayList<Integer>();
        arrayList.add(15);
        assertEquals("ua.lesson.lessons.Lesson_10.MyArrayList: [15], ;", arrayList.toString());
    }

    @Test
    public void remove() throws Exception {
        MyArrayList<Integer> arrayList=new MyArrayList<Integer>();
        arrayList.add(15);
        arrayList.add(15);
        arrayList.add(16);
        arrayList.remove(1);
        assertEquals("ua.lesson.lessons.Lesson_10.MyArrayList: [15], [16], ;", arrayList.toString());
    }

    @Test
    public void remove1(){
        MyArrayList<Integer> arrayList=new MyArrayList<Integer>();
        for(int i=0;i<10;i++)
            arrayList.add(i);
        arrayList.remove(1,7);
        assertEquals("ua.lesson.lessons.Lesson_10.MyArrayList: [0], [8], [9], ;", arrayList.toString());
    }

    @Test
    public void get() throws Exception {
        MyArrayList<Integer> arrayList=new MyArrayList<Integer>();
        arrayList.add(15);
        arrayList.add(25);
        arrayList.add(35);
        assertEquals(new Integer(25), arrayList.get(1));
    }


    @Test (expected=IndexOutOfBoundsException.class)
    public void getWithError() throws Exception{
        MyArrayList<Integer> arrayList=new MyArrayList<Integer>();
        arrayList.add(1);
        arrayList.get(1);
    }

    @Test (expected=IllegalStateException.class)
    public void getWithError1() throws Exception{
        MyArrayList<Integer> arrayList=new MyArrayList<Integer>();
        arrayList.get(0);
    }

    @Test
    public void expand() {
        MyArrayList<Integer> arrayList = new MyArrayList<Integer>();
        for (int i = 0; i < 15; i++)
            arrayList.add(i);
        assertEquals("ua.lesson.lessons.Lesson_10.MyArrayList: [0], [1], [2], [3], [4], [5]," +
                " [6], [7], [8], [9], [10], [11], [12], [13], [14], ;", arrayList.toString());
        assertEquals(15,arrayList.size());
    }

}