import java.util.*;

/**
 * 现有需求需要实现一个Set, 所有set的功能都需要具备，
 * 但是在remove元素的时候需要额外维护一个list,用来记录已经被删除过的元素的历史
 *
 * @param <E>
 */

public class HistorySet<E> implements Set<E> {

    private final Set<E> set = new HashSet<>();

    private final List<E> removeList = new ArrayList<>();


    @Override
    public int size() {
        return set.size();
    }

    @Override
    public boolean isEmpty() {
        return set.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return set.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return set.iterator();
    }

    @Override
    public Object[] toArray() {
        return set.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return set.toArray(a);
    }

    @Override
    public boolean add(E e) {
        return set.add(e);
    }

    @Override
    public boolean remove(Object o) {
        // 注意这里的remove需要成功才记录到history中
        if (set.remove(o)) {
            removeList.add((E) o);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return set.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return set.addAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return set.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return set.removeAll(c);
    }

    @Override
    public void clear() {
        set.clear();
    }

    @Override
    public String toString() {
        return "HistorySet{" +
                "set=" + set +
                ", removeList=" + removeList +
                '}';
    }
}
