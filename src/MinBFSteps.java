import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class MinBFSteps {

    /**
     * Queue used for BFS
     */
    private Queue<Integer> pile;
    /**
     * Set used to prune branches that have been visited
     */
    private HashSet<Integer> considered;
    /**
     * HashMap to store the steps leading to a particular number of tokens.
     * The value of a key is the number of tokens at t-1 steps if there were key tokens in t steps
     */
    private HashMap<Integer, Integer> values;
    /**
     * String used to output the steps
     */
    private String track;
    /**
     * No of tokens at the start of the game
     */
    private int tokens;
    /**
     * The minimum no of steps required to get to 1 token.
     */
    private int minSteps;

    /**
     * Constructor to initialize instance variables and invoke the bfsearch and calMoves methods
     * @param tokens No of tokens at the start of the game
     */
    public MinBFSteps(int tokens){
        this.tokens = tokens;
        pile = new LinkedList<>();
        considered = new HashSet<>();
        values = new HashMap<>();
        minSteps = 1;
        bfsearch();
        calMoves();
    }

    /**
     * Method that implements BFS using pile to get to 1 token
     */
    private void bfsearch(){

        int tokens = this.tokens;
        pile.add(tokens);

        while (true){
            // Dequeue the token to be visited
            tokens = pile.remove();

            // Stop the BFS when only one token is left
            if (tokens == 1) {
                break;
            }

            // Add a branch only if it a branch with that no of tokens hasn't already been visited
            if (!considered.contains(tokens)) {
                adder(tokens);
            }

            // Add the no of tokens to considered since that branch has been visited
            considered.add(tokens);
        }
    }

    /**
     * Method to add tokens to "pile" and keep track of the steps by adding tokens to "values"
     * @param tokens No of tokens we are considering
     */
    private void adder(int tokens){

        // If the no of tokens is divisible by 3 and (tokens/3) hasn't been visited
        if ((tokens % 3) == 0 && !considered.contains(tokens/3)){
            pile.add(tokens/3);
            values.putIfAbsent(tokens/3, tokens);
        }

        // If the no of tokens is divisible by 2 and (tokens/2) hasn't been visited
        if ((tokens % 2) == 0 && !considered.contains(tokens/2)){
            pile.add(tokens/2);
            values.putIfAbsent(tokens/2, tokens);
        }

        // If (tokens - 1) hasn't already been visited
        if (!considered.contains(tokens - 1)){
            pile.add(tokens - 1);
            values.putIfAbsent(tokens-1, tokens);
        }

    }


    /**
     * Method to re-trace the steps in the game
     */
    private void calMoves(){
        // String to store the steps trace
        String sb = "";

        //Start from 1 and trace back to the no of tokens at the start of the game
        int token = 1;

        while (token != this.tokens){
            // Add the trace
            sb = (" --> " + token) + sb;
            minSteps++;
            // Update token to the number of tokens in previous steps
            token = values.get(token);
        }

        sb = token + sb;

        // Update track to the calculated steps trace
        track = sb;
    }


    /**
     * Method to sequence of moves required
     * @return Trace of the steps in the game
     */
    public String getBFMoves(){
        return track;
    }

    /**
     * Method to return the total number of distinct states generated using breadth-first
     * @return Total number of nodes generated in BFS
     */
    public int solutionNodes(){
        return considered.size() + 1;
    }

    /**
     * Method to return the minimum number of steps using breadth-first search
     * @return Minimum no of steps required in the game
     */
    public int solutionSteps(){
        return minSteps - 1;
    }

}
