import java.util.*;

/**
 * 现有需求需要实现一个Set, 所有set的功能都需要具备，
 * 但是在remove元素的时候需要额外维护一个list,用来记录已经被删除过的元素的历史
 *
 * @param <E>
 */

public class HistorySet<E> implements Set<E> {

    private final Set<E> delegate;

    private final List<E> removeList = new ArrayList<>();

    public HistorySet(Set<E> set) {
        this.delegate = set;
    }

    @Override
    public int size() {
        return delegate.size();
    }

    @Override
    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return delegate.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return delegate.iterator();
    }

    @Override
    public Object[] toArray() {
        return delegate.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return delegate.toArray(a);
    }

    @Override
    public boolean add(E e) {
        return delegate.add(e);
    }

    @Override
    public boolean remove(Object o) {
        // 注意这里的remove需要成功才记录到history中
        if (delegate.remove(o)) {
            removeList.add((E) o);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return delegate.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return delegate.addAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return delegate.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return delegate.removeAll(c);
    }

    @Override
    public void clear() {
        delegate.clear();
    }

    @Override
    public String toString() {
        return "HistorySet{" +
                "set=" + delegate +
                ", removeList=" + removeList +
                '}';
    }
}
