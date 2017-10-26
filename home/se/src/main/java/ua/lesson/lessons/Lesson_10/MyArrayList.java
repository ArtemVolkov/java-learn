package ua.lesson.lessons.Lesson_10;

import java.util.Arrays;

/**
 * My own realisation of resizable array
 * @author Volkov A
 * @since 25.06.2017
 */

//just for training
public class MyArrayList<E> {
    private Object[] array;
    /**
     * Default size of array
     */
    private final int DEFAULT_SIZE=10;
    private int arraySize;
    /**
     * Coeficcient that determinies when array is almost full
     */
    private final double EXTENDS_COEF = 0.75;
    /**
     * Index to inserting some values
     * and real number of inserting elements
     */
    private int addPointer=0;

    /**
     * Default constructor creates array with DEFAULT_SIZE element
     */
    public MyArrayList(){
        array=new Object[DEFAULT_SIZE];
        arraySize=DEFAULT_SIZE;
    }

    /**
     * Method add 1 element to the ArrayList
     * @param e element to add
     *
     */
    public void add(E e){
        if(arraySize*EXTENDS_COEF<=addPointer)
            expand();

        array[addPointer++]=e;
    }

    /**
     * Method insert 1 element in the selected position
     * @param e element
     * @param index position in array
     * @throws IllegalArgumentException if index is invalid(<0 or >size)</0>
     */
    public void add(E e, int index){
        if(index<0 || index>addPointer) throw new IllegalArgumentException("Invalid index!");
        if(index == addPointer){
            this.add(e);
            return;
        }

        if(arraySize*EXTENDS_COEF<=addPointer)
            expand();

        System.arraycopy(array, index, array,index+1, addPointer-index);
        array[index]=e;
        addPointer++;


    }

    /**
     * Increase array size twice
     */
    private void expand(){
        Object[] temp=new Object[arraySize*2];
        System.arraycopy(array,0,temp,0,arraySize);
        array=temp;
        arraySize*=2;
    }

    /**
     * Method return number of elements in list
     * @return array size
     */
    public int size(){
        return addPointer;
    }

    /**
     * Method returns true if List contains no element
     * @return true if List contains no element
     */
    public boolean isEmpty(){
        return addPointer==0;
    }

    /**
     * Method removes element with index from list
     * @param index element index (0... size)
     * @throws  IndexOutOfBoundsException if index is not valid (<0 or > size)
     */
    public void remove(int index){
        if(index>-1 && index<addPointer){
            int elemMoved=addPointer-index-1;
            if(elemMoved>0){
                System.arraycopy(array,index+1,array,index, elemMoved);
                addPointer--;
            }

        }
        else throw new IndexOutOfBoundsException("Index < 0 or > size");
    }

    /**
     * Method removes few elements from list
     * @param index index of 1 element to remove
     * @param count count of removing elements
     * @throws IllegalArgumentException if index+count > size
     * @throws IndexOutOfBoundsException if index is not valid
     */
    public void remove(int index, int count ){
        if(index<0 || index>=addPointer) throw new IndexOutOfBoundsException("Index < 0 or > size");
        if(index+count>addPointer) throw new IllegalArgumentException("index + count > size");

        int elemMoved=addPointer-index-count;
        if(elemMoved>0 && count>0){
            System.arraycopy(array,index+count,array,index,elemMoved);
            addPointer-=count;
        }
    }

    /**
     * Method returns element with this index
     * @param index index of element
     * @return element with this index
     * @throws IndexOutOfBoundsException if index <0 or > size
     */
    public E get(int index){
        if(this.isEmpty()) throw new IllegalStateException("List has no elements!");
        if(index<0 || index>=addPointer) throw new IndexOutOfBoundsException("Invalid index");
        return (E)array[index];
    }

    public String toString(){
        StringBuilder result=new StringBuilder("");
        result.append(this.getClass().getName()+": ");
        for(int i=0;i<addPointer;i++){
            result.append("[");
            result.append(array[i]);
            result.append("], ");
        }
        result.append(";");
        return result.toString();

    }



}
