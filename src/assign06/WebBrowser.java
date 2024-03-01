package assign06;

import java.net.URL;
import java.util.NoSuchElementException;

/**
 * Web browser simulator using two stacks to back the
 * forward and back functions of a Web Browser.
 *
 * @author Austin Corbett, Dominik Jamrich
 * @version February 29, 2024
 */
public class WebBrowser {
    private LinkedListStack<URL> forward;
    private LinkedListStack<URL> backward;
    private URL current;

    /**
     * Default constructor for WebBrowser
     */
    public WebBrowser(){
        forward = new LinkedListStack<URL>();
        backward = new LinkedListStack<URL>();
        current = null;

    }

    /**
     * Constructor for WebBrowser
     * @param history will be loaded into the backward stack.
     *                First element of histroy will be the current element of stack.
     *
     */
    public WebBrowser(SinglyLinkedList<URL> history){
        forward = new LinkedListStack<URL>();
        backward = new LinkedListStack<URL>();
        current = history.getFirst();
        Object[] urlArr = history.toArray();
        for(int i = urlArr.length-1;i > 0;i--){
            backward.push((URL) urlArr[i]);
        }
    }

    /**
     * @param webpage: the URL you're simulating visiting.
     */
    public void visit(URL webpage){
        backward.push(current);
        current = webpage;
        forward.clear();
    }

    /**
     * Simulates going back in a web browser.
     * updates current webpage to last visted page.
     * @return the URL of the last page visited.
     * @throws NoSuchElementException
     */
    public URL back() throws NoSuchElementException {
        if(backward.isEmpty())
            throw new NoSuchElementException();
        forward.push(current);
        current = backward.pop();
        return current;
    }

    /**
     * Simulates going forward in a web browser.
     * Updates the current webpage as well
     * @return the URl of the webpage infront of current webpage.
     * @throws NoSuchElementException
     */
    public URL forward() throws NoSuchElementException{
        if(forward.isEmpty())
            throw new NoSuchElementException();
        backward.push(current);
        current = forward.pop();
        return current;

    }

    /**
     * @return the current and entire back stack as a SLL.
     */
    public SinglyLinkedList<URL> history(){
        SinglyLinkedList<URL> retSLL = new SinglyLinkedList<>();
        int length = 0;
        while(!backward.isEmpty()){
            forward.push(backward.pop());
            length++;
        }
        for(int i = length;i>0;i--){
            backward.push(forward.peek());
            retSLL.insertFirst(forward.pop());
        }
        retSLL.insertFirst(current);
        return retSLL;
    }
}
