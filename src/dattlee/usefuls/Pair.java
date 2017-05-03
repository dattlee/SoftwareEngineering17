package dattlee.usefuls;

/**
 * Used to store pairs of Generic Classes, Useful in the storage of a set of 2 different classed objects.
 *
 * @param <F> Any class
 * @param <S> Any Class
 *
 * @version 1.0
 * @author Dattlee
 */
public class Pair<F, S> {
    private F first;
    private S second;

    public Pair(F first, S second){
        this.first = first;
        this.second = second;
    }

    /**
     * Returns the first object F
     * @return The first object F
     */
    public F getFirst() {
        return first;
    }

    /**
     * Returns the second object S
     * @return The second object S
     */
    public S getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;

        Pair<?, ?> pair = (Pair<?, ?>) o;

        if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
        return second != null ? second.equals(pair.second) : pair.second == null;

    }

    @Override
    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        result = 31 * result + (second != null ? second.hashCode() : 0);
        return result;
    }
}