package org.codeus.webinar.debugging.app.packaging;


public sealed interface Package {}

record Bag(double volume) implements Package {}
record Bottle(double volume) implements Package {}