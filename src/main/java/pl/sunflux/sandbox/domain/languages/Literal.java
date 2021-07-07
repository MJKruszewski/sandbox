package pl.sunflux.sandbox.domain.languages;

class Literal extends Generator {
    private String value;

    Literal(String value) {
        super();
        this.value = value;
    }

    public double combinations() {
        return 1;
    }

    public int min() {
        return this.value.length();
    }

    public int max() {
        return this.value.length();
    }

    public String toString() {
        return this.value;
    }
}
