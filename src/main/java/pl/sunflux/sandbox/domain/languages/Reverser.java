package pl.sunflux.sandbox.domain.languages;

class Reverser extends Generator {
    Reverser(Generator generator) {
        super(new Generator[]{generator});
    }

    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append(LanguageGenerator.reverseString(super.toString()));

        return result.toString();
    }
}
