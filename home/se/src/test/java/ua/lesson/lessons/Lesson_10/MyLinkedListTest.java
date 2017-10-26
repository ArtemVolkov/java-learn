package ua.lesson.lessons.Lesson_10;

import org.junit.Test;

import static org.junit.Assert.*;

public class MyLinkedListTest {
    @Test
    public void add() throws Exception {
        MyLinkedList<Integer> list=new MyLinkedList<Integer>();
        list.addLast(2);
        list.addFirst(1);
        assertEquals("ua.lesson.lessons.Lesson_10.MyLinkedList: [ 0(1), 1(2) ];", list.toString());
        list.addLast(4);
        list.addFirst(0);
        assertEquals("ua.lesson.lessons.Lesson_10.MyLinkedList: [ 0(0), 1(1), 2(2), 3(4) ];", list.toString());

        //I try all indexes, no bugs
        list.add(3,3);

        assertEquals("ua.lesson.lessons.Lesson_10.MyLinkedList: [ 0(0), 1(1), 2(2), 3(3), 4(4) ];", list.toString());
        assertEquals(5,list.size());
    }

    @Test
    public void addFirst() throws Exception {
        MyLinkedList<Integer> list=new MyLinkedList<Integer>();
        list.addFirst(2);
        list.addFirst(1);
        list.addFirst(0);

        assertEquals("ua.lesson.lessons.Lesson_10.MyLinkedList: [ 0(0), 1(1), 2(2) ];", list.toString());
        assertEquals(3,list.size());
    }

    @Test
    public void removeFirst() throws Exception {
        MyLinkedList<Integer> list=new MyLinkedList<Integer>();
        list.addFirst(1);
        list.addFirst(0);
        list.addFirst(1);

        list.removeFirst();

        assertEquals("ua.lesson.lessons.Lesson_10.MyLinkedList: [ 0(0), 1(1) ];", list.toString());
        assertEquals(2,list.size());
    }

    @Test
    public void removeLast() throws Exception {
        MyLinkedList<Integer> list=new MyLinkedList<Integer>();
        list.addFirst(2);
        list.addFirst(1);
        list.addFirst(0);

        list.removeLast();

        assertEquals("ua.lesson.lessons.Lesson_10.MyLinkedList: [ 0(0), 1(1) ];", list.toString());
        assertEquals(2,list.size());
    }

    @Test
    public void remove() throws Exception {
        MyLinkedList<Integer> list=new MyLinkedList<Integer>();
        list.addFirst(3);
        list.addFirst(2);
        list.addFirst(1);
        list.addFirst(0);

        list.remove(2);

        assertEquals("ua.lesson.lessons.Lesson_10.MyLinkedList: [ 0(0), 1(1), 2(3) ];", list.toString());
        assertEquals(3,list.size());
    }

    @Test
    public void addLast() throws Exception {
        MyLinkedList<Integer> list=new MyLinkedList<Integer>();
        list.addLast(0);
        list.addLast(1);
        list.addLast(2);

        assertEquals("ua.lesson.lessons.Lesson_10.MyLinkedList: [ 0(0), 1(1), 2(2) ];", list.toString());
        assertEquals(3,list.size());
    }

    @Test
    public void search() throws Exception {
        MyLinkedList<Integer> list=new MyLinkedList<Integer>();
        list.addLast(0);
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);

        assertEquals(new Integer(0) , list.search(0));
        assertEquals(new Integer(1) , list.search(1));
        assertEquals(new Integer(2) , list.search(2));
        assertEquals(new Integer(3) , list.search(3));
        assertEquals(new Integer(4) , list.search(4));
    }


    @Test
    public void toStringTest() throws Exception {
        MyLinkedList<Integer> list=new MyLinkedList<Integer>();
        list.addFirst(2);
        list.addFirst(0);
        assertEquals("ua.lesson.lessons.Lesson_10.MyLinkedList: [ 0(0), 1(2) ];", list.toString());
    }

}