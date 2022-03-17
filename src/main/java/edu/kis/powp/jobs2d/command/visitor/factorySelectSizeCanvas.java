package edu.kis.powp.jobs2d.command.visitor;

abstract class Size {
    public int width;
    public int height;

    public abstract int getWidth();

    public abstract int getHeight();
}

class A0 extends Size {

    @Override
    public int getWidth() {
        return 841;
    }

    @Override
    public int getHeight() {
        return 1189;
    }
}


class A1 extends Size {


    @Override
    public int getWidth() {
        return 594;
    }

    @Override
    public int getHeight() {
        return 841;
    }
}

class A2 extends Size {


    @Override
    public int getWidth() {
        return 420;
    }

    @Override
    public int getHeight() {
        return 594;
    }
}

class A3 extends Size {


    @Override
    public int getWidth() {
        return 297;
    }

    @Override
    public int getHeight() {
        return 420;
    }
}

class A4 extends Size {

    @Override
    public int getWidth() {
        return 210;
    }

    @Override
    public int getHeight() {
        return 297;
    }
}

class A5 extends Size {


    @Override
    public int getWidth() {
        return 148;
    }

    @Override
    public int getHeight() {
        return 210;
    }
}

class A6 extends Size {


    @Override
    public int getWidth() {
        return 105;
    }

    @Override
    public int getHeight() {
        return 148;
    }
}

class A7 extends Size {


    @Override
    public int getWidth() {
        return 74;
    }

    @Override
    public int getHeight() {
        return 105;
    }
}

class A8 extends Size {


    @Override
    public int getWidth() {
        return 52;
    }

    @Override
    public int getHeight() {
        return 74;
    }
}

class A9 extends Size {


    @Override
    public int getWidth() {
        return 37;
    }

    @Override
    public int getHeight() {
        return 52;
    }
}

class A10 extends Size {


    @Override
    public int getWidth() {
        return 26;
    }

    @Override
    public int getHeight() {
        return 37;
    }
}

public class factorySelectSizeCanvas {


    public Size getSize(String A) {

        if (A.compareToIgnoreCase("A0") == 0) {
            return new A0();
        }
        if (A.compareToIgnoreCase("A1") == 0) {
            return new A1();
        }
        if (A.compareToIgnoreCase("A2") == 0) {
            return new A2();
        }
        if (A.compareToIgnoreCase("A3") == 0) {
            return new A3();
        }
        if (A.compareToIgnoreCase("A4") == 0) {
            return new A4();
        }
        if (A.compareToIgnoreCase("A5") == 0) {
            return new A5();
        }
        if (A.compareToIgnoreCase("A6") == 0) {
            return new A6();
        }
        if (A.compareToIgnoreCase("A7") == 0) {
            return new A7();
        }
        if (A.compareToIgnoreCase("A8") == 0) {
            return new A8();
        }
        if (A.compareToIgnoreCase("A9") == 0) {
            return new A9();
        }
        if (A.compareToIgnoreCase("A10") == 0) {
            return new A10();
        }
        return null;
    }
}