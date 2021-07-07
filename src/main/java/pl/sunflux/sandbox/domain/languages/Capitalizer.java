package pl.sunflux.sandbox.domain.languages;

class Capitalizer extends Generator {
    Capitalizer(Generator generator) {
        super(new Generator[]{generator});
    }

    public String toString() {
        String str = super.toString();

        if (str.length() <= 0) {
            return "";
        }

        return str.substring(0, 1).toUpperCase() + str.substring(1, 2).toLowerCase();
    }
}
