package p28_09_2023;

public class FakeDriver {
    public int findNextNumber(int n) throws Exception {
        //radi samo za pozitivne

        if(n < 0){
            throw new ITBootcampException("Ne moze negativan broj! Uneo si "+n);
        }

        return n+1;
    }
}
