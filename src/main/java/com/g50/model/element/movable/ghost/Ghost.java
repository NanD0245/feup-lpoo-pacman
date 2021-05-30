package com.g50.model.element.movable.ghost;

import com.g50.states.GhostState;
import com.g50.model.element.Position;
import com.g50.model.element.fixed.noncollectable.Target;
import com.g50.model.element.movable.MovableElement;
import com.g50.model.element.movable.Orientation;
import com.g50.model.element.movable.ghost.strategy.GhostStrategy;

public abstract class Ghost extends MovableElement {
    private final Target target;
    private final GhostStrategy strategy;
    private GhostState state;
    private final GhostState startState;
    private final Orientation startOrientation;

    public Ghost(String name, Position position, Orientation orientation,
                 GhostState state, GhostStrategy strategy, Target target) {
        super(name, position, orientation);
        this.target = target;
        this.strategy = strategy;
        this.state = state;
        this.startState = state;
        this.startOrientation = orientation;
    }

    public Target getTarget() {
        return target;
    }

    public GhostState getState() {
        return state;
    }

    public GhostStrategy getStrategy() {
        return strategy;
    }

    public void setState(GhostState state) {
        this.state = state;
    }

    public void reset() {
        this.state = startState;
        this.setOrientation(this.startOrientation);
        this.strategy.resetDotLimit();
        setDefaultFramesPerPosition();
    }
}
