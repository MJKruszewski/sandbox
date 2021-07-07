package pl.sunflux.sandbox.domain.languages;

class Collapser extends Generator {
    Collapser(Generator generator) {
        super(new Generator[]{generator});
    }

    public String toString() {
        String str = super.toString();
        String out = "";
        int cnt = 0;
        char pch = '0';

        for (int i = 0; i < str.length(); i++) {
            char chr = str.charAt(i);
            if (chr == pch) {
                cnt++;
            } else {
                cnt = 0;
            }
            int mch = 2;
            switch (chr) {
                case 'a':
                case 'h':
                case 'i':
                case 'j':
                case 'q':
                case 'u':
                case 'v':
                case 'w':
                case 'x':
                case 'y':
                    mch = 1;
            }
            if (cnt < mch) {
                out += chr;
            }
            pch = chr;
        }
        return out;
    }
}
