class MyFirstClass {
    public static void main(String[] s) {
        MySecondClass o = new MySecondClass(5, 2);
        System.out.println(o.toSubstract());
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                o.setFirstValue(i);
                o.setSecondValue(j);
                System.out.print(o.toSubstract());
                System.out.print(" ");
            }
            System.out.println();
        }

    }
}

class MySecondClass {
    private int firstValue;
    private int secondValue;

    MySecondClass(int firstValue, int secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public int getFirstValue() {
        return firstValue;
    }

    void setFirstValue(int newFirstValue) {
        firstValue = newFirstValue;
    }

    int getSecondValue() {
        return secondValue;
    }

    void setSecondValue(int newSecondValue) {
        secondValue = newSecondValue;
    }

    int toSubstract() {
        return firstValue - secondValue;
    }
}
