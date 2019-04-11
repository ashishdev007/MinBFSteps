import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class MinBFSteps {

    private Queue<Integer> pile;
    private HashSet<Integer> considered;
    private int tokens;
    private int minSteps;
    private int nodeCount;

    public MinBFSteps(int tokens){
        this.tokens = tokens;
        pile = new LinkedList<>();
        considered = new HashSet<>();
        minSteps = 0;
        nodeCount = 0;
        bfsearch();
    }

    private void bfsearch(){

        pile.add(tokens);
        boolean flag = false;

        while (!flag){
            tokens = pile.remove();
            System.out.println(tokens);

            if (tokens == 1) {
                break;
            }

            if (!considered.contains(tokens)) {
                adder(tokens);
            }

            considered.add(tokens);
        }
        System.out.println(minSteps);
    }

    private void adder(int tokens){

        minSteps++;

        if ((tokens % 2) == 0){
            pile.add(tokens/2);
            nodeCount++;
        }
        if ((tokens % 3) == 0){
            pile.add(tokens/3);
            nodeCount++;
        }

        pile.add(tokens - 1);
        nodeCount++;
    }



}
