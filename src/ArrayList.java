public class ArrayList implements IntegerList {

    private Integer[] arr;
    private final static int INITIAL_SIZE = 1;

    public ArrayList() {
        arr = new Integer[INITIAL_SIZE];
    }

    public ArrayList(Integer[] arr) {
        this.arr = arr;
    }

    private void organizer() {
        Integer[] array = new Integer[this.size()];
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                array[j] = arr[i];
                ++j;
            }
        }
        arr = array;
    }

    public Integer length() {
        return arr.length;
    }

    @Override
    public Integer add(Integer item) {
        checkNullItem(item);

        if (this.size() == arr.length) {
            grow();
        }

        arr[this.size()] = item;
        return item;
    }

    private void grow() {
        Integer[] newArray;
        int newSize = (int) Math.ceil(arr.length * 1.5);
        newArray = new Integer[newSize];
        System.arraycopy(arr, 0, newArray, 0, arr.length);
        arr = newArray;
    }

    @Override
    public Integer add(int index, Integer item) {
        checkNullItem(item);

        if (index >= arr.length) {
            throw new ArrayListException("Index doesn't exist.");
        }
        int newSize = this.size() + 1;
        Integer[] array = new Integer[newSize];
        for (int i = 0, j = 0; j < newSize; i++, j++) {
            if (j < index) {
                array[j] = arr[i];
            } else if (j > index) {
                array[j] = arr[i];
            } else {
                array[j] = item;
                i--;
            }
        }
        arr = array;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        checkNullItem(item);
        if (index >= arr.length) {
            throw new ArrayListException("Index doesn't exist.");
        }
        arr[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        checkNullItem(item);
        boolean exception = true;
        for (int i = 0; i < this.size(); i++) {
            if (arr[i].equals(item)) {
                arr[i] = null;
                exception = false;
            }
        }
        if (exception) {
            throw new ArrayListException(String.format("Number \"%s\" wasn't found.", item));
        }
        this.organizer();
        return item;
    }

    @Override
    public Integer remove(int index) {
        if (index >= arr.length) {
            throw new ArrayListException("Index doesn't exist.");
        }
        Integer item = arr[index];
        arr[index] = null;
        this.organizer();
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        checkNullItem(item);
        for (int i = 0; i < this.size(); i++) {
            if (arr[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Integer item) {
        checkNullItem(item);
        for (int i = 0; i < this.size(); i++) {
            if (arr[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        checkNullItem(item);
        for (int i = this.size() - 1, j = 0; i >= 0; i--, j++) {
            if (arr[i].equals(item)) {
                return j;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        if (index >= arr.length) {
            throw new ArrayListException("Index doesn't exist.");
        }
        return arr[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (otherList == null) {
            throw new ArrayListException("List is empty.");
        }
        if (this.size() != otherList.size()) {
            return false;
        }
        for (int i = 0; i < this.size(); i++) {
            if (!this.get(i).equals(otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        int arraySizeCounter = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                arraySizeCounter++;
            }
        }
        return arraySizeCounter;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public void clear() {
        int size = this.size();
        for (int i = 0; i < size; i++) {
            arr[i] = null;
        }
    }

    @Override
    public ArrayList toArray() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.size(); i++) {
            arrayList.add(this.get(i));
        }
        return arrayList;
    }

    public String toString() {
        return this.toString("");
    }

    public String toString(String divider) {
        StringBuilder res = new StringBuilder();
        this.organizer();
        for (int i = 0; i < this.size(); i++) {
            res.append(this.get(i)).append(divider);
        }
        return res.substring(0, res.length() - divider.length());
    }

    private void checkNullItem(Integer item) {
        if (item == null) {
            throw new ArrayListException("Item can't be Null.");
        }
    }

    private void toSwap(int in, int i) {
        int buf = this.get(i);
        this.set(i, this.get(in));
        this.set(in, buf);
    }

    public ArrayList sortBubble() {
        int elems = this.length();
        for (int out = elems - 1; out >= 1; out--) {
            for (int in = 0; in < out; in++) {
                if (this.get(in) > this.get(in + 1)) {
                    toSwap(in, in + 1);
                }
            }
        }
        return this;
    }

    public ArrayList sortSelection() {
        for (int i = 0; i < this.length() - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < this.size(); j++) {
                if (this.get(j) < this.get(minElementIndex)) {
                    minElementIndex = j;
                }
            }
            toSwap(i, minElementIndex);
        }
        return this;
    }

    public ArrayList sortInsertion() {
        for (int left = 0; left < this.size(); left++) {
            int value = this.get(left); // Вытаскиваем значение элемента
            int i = left - 1; // Перемещаемся по элементам, которые перед вытащенным элементом
            for (; i >= 0; i--) {
                if (value < this.get(i)) { // Если вытащили значение меньшее — передвигаем больший элемент дальше
                    this.set(i + 1, this.get(i));
                } else {// Если вытащенный элемент больше — останавливаемся
                    break;
                }
            }
            this.set(i + 1, value); // В освободившееся место вставляем вытащенное значение
        }
        return this;
    }

    public boolean containsBinary(Integer element) {
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (element == arr[mid]) {
                return true;
            }

            if (element < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

}
