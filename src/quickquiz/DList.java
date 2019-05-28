/**
 * **************************************************************************
 */
/*                                                                           */
/*                    Doubly-Linked List Manipulation                        */
/*                                                                           */
/*                     January 1998, Toshimi Minoura                         */
/*                                                                           */
/**
 * **************************************************************************
 */
// Filename: Doubly-LinkedList_ToshimiMinoura
// Source:   TBA
package quickquiz;

// A LLNode is a node in a doubly-linked list.
class LLNode
{              // class for nodes in a doubly-linked list

    LLNode prev;              // previous LLNode in a doubly-linked list
    LLNode next;              // next LLNode in a doubly-linked list
   
    String qTopic;
    int qQuestionNumber;
    int qFailures; 

//AssocWords myWords;
    //public char data;       // data stored in this LLNode

    LLNode()
    {                // constructor for head LLNode 
        prev = this;           // of an empty doubly-linked list
        next = this;
        qTopic = " ";
        qQuestionNumber = 0;
        qFailures = 0;
//    myWords.Word1 = "Yellow";
//    myWords.Word2 = "No";
        // data = 'H';           // not used except for printing data in list head
    }

    LLNode(String w1, int w2, int w3)
    {       // constructor for a LLNode with data
        prev = null;
        next = null;
      
        qTopic = w1;
        qQuestionNumber = w2;
        qFailures = w3;
        
        //this.data = data;     // set argument data to instance variable data
    }

    public void append(LLNode newNode)
    {  // attach newNode after this LLNode
        newNode.prev = this;
        newNode.next = next;
        if (next != null)
        {
            next.prev = newNode;
        }
        next = newNode;
        System.out.println("LLNode with data " + newNode.qTopic
                + " appended after LLNode with data " + qTopic);
    }

    public void insert(LLNode newNode)
    {  // attach newNode before this LLNode
        newNode.prev = prev;
        newNode.next = this;
        prev.next = newNode;
        prev = newNode;
        System.out.println("LLNode with data " + newNode.qTopic
                + " inserted before LLNode with data " + qTopic);
    }

    public void remove()
    {              // remove this LLNode
        next.prev = prev;                 // bypass this LLNode
        prev.next = next;
        System.out.println("LLNode with data " + qTopic + " removed");
    }
    public String toString(){
        return this.qTopic + " - " + this.qQuestionNumber;
    }
}

class DList
{

    LLNode head;

    public DList(String s1, int s2, int s3)
    {
        head = new LLNode(s1, s2, s3);
    }

    public LLNode find(String wrd1)
    {          // find LLNode containing x
        for (LLNode current = head.next; current != head; current = current.next)
        {
            if (current.qTopic.compareToIgnoreCase(wrd1) == 0)
            {        // is x contained in current LLNode?
                System.out.println("Data " + wrd1 + " found");
                return current;               // return LLNode containing x
            }
        }
        System.out.println("Data " + wrd1 + " not found");
        return null;
    }

    //This Get method Added by Matt C
    public LLNode get(int i)
    {
        LLNode current = this.head;
        if (i < 0 || current == null)
        {
            throw new ArrayIndexOutOfBoundsException();
        }
        while (i > 0)
        {
            i--;
            current = current.next;
            if (current == null)
            {
                throw new ArrayIndexOutOfBoundsException();
            }
        }
        return current;
    }

    public String toString()
    {
        String str = "";
        if (head.next == head)
        {             // list is empty, only header LLNode
            return "List Empty";
        }
        str = "list content = ";
        for (LLNode current = head.next; current != head && current != null; current = current.next)
        {
            str = str + current.qTopic;
        }
        return str;
    }

    public void print()
    {                  // print content of list
        if (head.next == head)
        {             // list is empty, only header LLNode
            System.out.println("list empty");
            return;
        }
        System.out.print("list content = ");
        for (LLNode current = head.next; current != head.prev; current = current.next)
        {
            System.out.print(" " + current.qTopic);
        }
        System.out.println("");
    }

}
