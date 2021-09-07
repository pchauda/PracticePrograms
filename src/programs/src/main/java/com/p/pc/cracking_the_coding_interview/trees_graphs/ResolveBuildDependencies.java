package com.p.pc.cracking_the_coding_interview.trees_graphs;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given a list of projects and its dependencies, figure out the dependency resolution order for all projects.
 * If there is a cyclic dependency then flag it out as and error.
 *
 * Approach:
 *  This problem can be solved using the graph approach easily. A topological sort order needs to be created for given
 *  projects. If a cyclic dependency is detected then throw exception.
 */
public class ResolveBuildDependencies {
    static ResolveBuildDependencies obj = new ResolveBuildDependencies();

    public static void main(String[] args) {
        String[] projectNames = new String[] {"a", "b", "c", "d", "e", "f"};
        // dependency (a, b) means b is dependent on a
        String[][] dependencies = new String[][] {
                {"a", "b"},
                {"b", "c"},
                {"f", "e"},
                {"e", "a"},
                {"d", "c"},
                {"c", "e"} // this dependency will cause cyclic dependency
        };
        ArrayList<Project> projects = obj.createProjects(projectNames);
        Graph g = obj.createGraph(projects, dependencies);
        try {
            Stack<Project> buildOrder = obj.createBuildOrder(g);
            System.out.println("Projects build order is: ");
            while(!buildOrder.empty()) {
                System.out.print(buildOrder.pop().name + " ");
            }
        } catch (CyclicDependencyException e) {
            System.out.println("Cyclic dependency, build can't be completed.");
        }
    }
    // Perform Topological sort operation by keeping track of the Project state
    private Stack<Project> createBuildOrder(Graph g) throws CyclicDependencyException {
        Stack<Project> stack = new Stack<>();
        for(Project prj : g.projects) {
            if(prj.state == State.NOT_STARTED) {
                performTopologicalSort(prj, stack);
            }
        }
        return stack;
    }

    private void performTopologicalSort(Project prj, Stack<Project> stack) throws CyclicDependencyException {
        if(prj.state == State.IN_PROGRESS)
            throw new CyclicDependencyException();

        prj.state = State.IN_PROGRESS;
        for(Project d : prj.dependents) {
            if(d.state != State.COMPLETED)
                performTopologicalSort(d, stack);
        }
        prj.state = State.COMPLETED;
        stack.push(prj);
    }

    private Graph createGraph(ArrayList<Project> prjs, String[][] dependencies) {
        Graph g = new Graph(prjs);
        for(String[] d : dependencies) {
            g.addDependency(d[0], d[1]);
        }
        g.printGraph();
        return g;
    }

    private ArrayList<Project> createProjects(String[] projects) {
        ArrayList<Project> retVal = new ArrayList<>();
        Arrays.stream(projects).forEach(t -> retVal.add(new Project(t)));
        return retVal;
    }

    static class Graph {
        ArrayList<Project> projects;
        Map<String, Project> projectMap;

        Graph(ArrayList<Project> projects) {
            this.projects = projects;
            projectMap = this.projects.stream().collect(Collectors.toMap(t -> t.name, t -> t));
        }

        void addDependency(String u, String v) {
            projectMap.get(u).addDependentChild(projectMap.get(v));
        }

        public void printGraph() {
            for(Project prj : projects) {
                StringJoiner joiner = new StringJoiner(" -> ", "[", "}");
                joiner.add(prj.name + ":" + prj.state);
                prj.dependents.stream().forEach(t -> joiner.add(t.name + ":" + t.state));
                System.out.println(joiner);
            }
        }
    }

    enum State { NOT_STARTED, IN_PROGRESS, COMPLETED}
    static class Project {
        String name;
        State state = State.NOT_STARTED;
        ArrayList<Project> dependents;
        Set<String> dependentsSet = new HashSet<>(); // this set is used to avoid adding duplicate dependents

        Project(String name) {
            this.name = name;
            this.dependents = new ArrayList<>();
        }

        void addDependentChild(Project child) {
            if(!dependentsSet.contains(child.name)) {
                dependents.add(child);
                dependentsSet.add(child.name);
            }
        }
    }

    class CyclicDependencyException extends Exception {

    }
}
