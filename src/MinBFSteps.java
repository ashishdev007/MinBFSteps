import java.util.LinkedList;
import java.util.Queue;

public class MinBFSteps {

    private Queue<Integer> pile;
    private int tokens;
    private int minSteps;
    private int nodeCount;

    public MinBFSteps(int tokens){
        this.tokens = tokens;
        pile = new LinkedList<>();
        minSteps = 0;
        nodeCount = 0;
    }

    private void bfsearch(int tokens){

        pile.add(tokens);
        boolean flag = false;

        while (!flag){
            tokens = pile.remove();
            System.out.println(tokens);

            if (tokens == 1)
                break;

            adder(tokens);
        }
    }

    private void adder(int tokens){
        if ((tokens % 2) == 0){
            pile.add(tokens/2);
        }
        if ((tokens % 3) == 0){
            pile.add(tokens/3);
        }
        pile.add(tokens - 1);
    }



}
