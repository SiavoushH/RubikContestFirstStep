package com.rubikcontest;

import java.util.List;

public class Puzzle {

    private final SimpleForm startingForm;
    private final SimpleForm scrambledForm;
    private final List<String> toScrambleMoves;
    private final List<String> toSolveMoves;

    public int getSize() {
        return toScrambleMoves.size();
    }

    private Puzzle(Builder builder) {
        startingForm = builder.startingForm;
        scrambledForm = builder.scrambledForm;
        toScrambleMoves = builder.toScrambleMoves;
        toSolveMoves = builder.toSolveMoves;
        validate();
    }

    public SimpleForm getStartingForm() {
        return startingForm;
    }

    public SimpleForm getScrambledForm() {
        return scrambledForm;
    }

    public List<String> getToScrambleMoves() {
        return toScrambleMoves;
    }

    private void validate() {
        if (startingForm == null || scrambledForm == null || toScrambleMoves == null || toSolveMoves == null) {
            throw new IllegalArgumentException("All fields must be set.");
        }
        if (toScrambleMoves.size() != toSolveMoves.size()) {
            throw new IllegalArgumentException("The number of moves to scramble and solve must be equal.");
        }

        if (startingForm.equals(scrambledForm)) {
            throw new IllegalArgumentException("The scrambled form must be a valid scrambled form of the starting form.");
        }
    }


    public static final class Builder {
        private SimpleForm startingForm;
        private SimpleForm scrambledForm;
        private List<String> toScrambleMoves;
        private List<String> toSolveMoves;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder startingForm(SimpleForm val) {
            startingForm = val;
            return this;
        }

        public Builder scrambledForm(SimpleForm val) {
            scrambledForm = val;
            return this;
        }

        public Builder toScrambleMoves(List<String> val) {
            toScrambleMoves = val;
            return this;
        }

        public Builder toSolveMoves(List<String> val) {
            toSolveMoves = val;
            return this;
        }

        public Puzzle build() {
            return new Puzzle(this);
        }
    }
}
