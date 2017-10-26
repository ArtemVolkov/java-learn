package ua.lesson.lessons.Lesson_10;

public class MyLinkedList<E> {
    Elem<E> head;
    private int size=0;

    private class Elem<E>{
        E element;
        Elem<E> next;
        Elem<E> prev;
        boolean isHead=false;

        Elem(E e, Elem<E> n, Elem<E> p){
            this.element=e;
            this.next=n;
            this.prev=p;
        }

        public void erase(){
            element=null;
            next=null;
            prev=null;
        }

    }

    public MyLinkedList(){
        head=new Elem<E>(null,null,null);
        head.prev=head.next=head;
        head.element=null;
        head.isHead=true;
    }


    /**
     * Method adds new Element in the end of this List
     * @param element new element
     */
    public void addLast(E element){
        Elem<E> newElem=new Elem<E>(element, head, head.prev);

        // учстановка предыидущему перед новым элементом ссылку "след" на нов елем
        head.prev.next=newElem;
        // установка главе списка ссылке "пред" на новый елемент
        newElem.next.prev=newElem;
        size++;
    }

    /**
     * Method adds new Element in the head of this list
     * @param element new element
     */
    public void addFirst(E element){
        Elem<E> newElem= new Elem<E>(element, head.next ,head);

        //перв елемент новый
        head.next=newElem;
        //следующему после нового ссылку на предидущ установить на нов елем
        newElem.next.prev=newElem;
        size++;

    }

    /**
     * Method removes first element from list
     * @return deleted element
     */
    public E removeFirst(){
        if(size==0) throw new IllegalStateException("List is empty");
        Elem<E> res=head.next;
        E result=res.element;

        //голове списка.след= след от удаляекого
        head.next=res.next;
        //новому следующему.предидущ присв голову
        head.next.prev=head;

        //очистка и возврат
        res.erase();
        size--;
        return result;
    }

    /**
     * Method removes last element from list
     * @return deleted element
     */
    public E removeLast(){
        if(size==0) throw new IllegalStateException("List is empty");

        Elem<E> res=head.prev;
        E result=res.element;

        //новому последнему присвоить некст=голову списка
        res.prev.next=head;
        //последнему элементу присвоить предыдущий перед результатом
        head.prev=res.prev;

        //очистка и взоврат
        res.erase();
        size--;
        return result;
    }

    /**
     * Method search Element with links in list
     * @param index index of searched element
     * @return searched Element with links
     */
    private Elem<E> searchElem(int index){
        if(index<0 || index>=size) throw new IllegalArgumentException("Invalid index!");

        Elem<E> result=head;
        if(index< size/2){
            for(int i=0;i<=index;i++){
                result=result.next;
            }
        }
        else{
            //и идет до индекса, т.к нужен лишний шаг на переход от хедера к концу списка
            for(int i=size;i>index;i--)
                result=result.prev;
        }

        return result;
    }

    /**
     * Method removes element with index
     * @param index index of removed element
     * @return removed element
     */
    public E remove(int index){
        if(size==0) throw new IllegalStateException("List is empty");
        Elem<E> res=this.searchElem(index);

        //Предыдущему.след установить ссылку на след после удаляемого
        res.prev.next=res.next;
        // След.пред уст ссылку на предыдущий перед удаляемым
        res.next.prev=res.prev;

        E result=res.element;
        res.erase();
        size--;
        return result;
    }

    /**
     * Method add new element on this index
     * @param element element
     * @param index position
     */
    public void add(E element, int index){
        //если вставлять в конец листа, то нужна ссskrf на след елем - голову
        Elem<E> findedElem=index == size? head: this.searchElem(index);
        Elem<E> newElem=new Elem<E>(element, findedElem, findedElem.prev);

        findedElem.prev.next=newElem;
        findedElem.prev=newElem;

        size++;
    }

    /**
     * Method search element with index in list
     * @param index element index
     * @return searched element
     */
    public E  search(int index){
        Elem<E> searchedElem=this.searchElem(index);
        return searchedElem.element;
    }

    public int size(){
        return size;
    }

    public String toString(){
        StringBuilder result=new StringBuilder("");
        result.append(this.getClass().getName()+": [ ");
        Elem<E> elem=head;
        for(int i=0;i<size-1;i++){
            elem=elem.next;
            result.append(i+"("+elem.element+"), ");
        }
        elem=elem.next;
        result.append(size-1+"("+elem.element+") ];");
        return result.toString();
    }


}
