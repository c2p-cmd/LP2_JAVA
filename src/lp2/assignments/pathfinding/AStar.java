package lp2.assignments.pathfinding;

import java.util.*;

public class AStar {
    public static Node aStar(final Node start, final Node target){
        final PriorityQueue<Node> closedList = new PriorityQueue<>();
        final PriorityQueue<Node> openList = new PriorityQueue<>();

        start.fScore = start.gScore + start.hScore;
        openList.add(start);

        while(!openList.isEmpty()) {
            final Node n = openList.peek();
            if(n == target) {
                return n;
            }

            for(Node.Edge edge : n.neighbors) {
                final Node currentNode = edge.node;
                final double totalWeight = n.gScore + edge.weight;

                if(!openList.contains(currentNode) && !closedList.contains(currentNode)){
                    currentNode.parent = n;
                    currentNode.gScore = totalWeight;
                    currentNode.fScore = currentNode.gScore + currentNode.hScore;
                    openList.add(currentNode);
                } else {
                    if(totalWeight < currentNode.gScore) {
                        currentNode.parent = n;
                        currentNode.gScore = totalWeight;
                        currentNode.fScore = currentNode.gScore + currentNode.hScore;

                        if(closedList.contains(currentNode)) {
                            closedList.remove(currentNode);
                            openList.add(currentNode);
                        }
                    }
                }
            }

            openList.remove(n);
            closedList.add(n);
        }
        return null;
    }

    public static void printPath(final Node target){
        Node n = target;

        if(n==null)
            return;

        List<Integer> ids = new ArrayList<>();

        while(n.parent != null){
            ids.add(n.id);
            n = n.parent;
        }
        ids.add(n.id);
        Collections.reverse(ids);

        for(int id : ids){
            System.out.print(id + " ");
        }
        System.out.println();
    }
    // And now, let's construct a graph and call this method:

    public static void main1(String[] args) {
        final Node head = new Node(3);
        head.gScore = 0;

        final Node n1 = new Node(2);
        final Node n2 = new Node(2);
        final Node n3 = new Node(2);

        head.addBranch(1, n1);
        head.addBranch(5, n2);
        head.addBranch(2, n3);
        n3.addBranch(1, n2);

        final Node n4 = new Node(1);
        final Node n5 = new Node(1);
        final Node target = new Node(0);

        n1.addBranch(7, n4);
        n2.addBranch(4, n5);
        n3.addBranch(6, n4);

        n4.addBranch(3, target);
        n5.addBranch(1, n4);
        n5.addBranch(3, target);

        final Node res = aStar(head, target);
        printPath(res);
    }

    public static void main(String[] args) {
        final Node startA = new Node(10);
        startA.gScore = 0;

        final Node nodeB = new Node(8);
        final Node nodeC = new Node(5);
        final Node nodeD = new Node(7);
        final Node nodeE = new Node(3);
        final Node nodeF = new Node(6);
        final Node nodeG = new Node(5);
        final Node nodeH = new Node(3);
        final Node nodeI = new Node(1);
        final Node target = new Node(0);

        startA.addBranch(6, nodeB);
        startA.addBranch(3, nodeF);

        nodeB.addBranch(8, nodeC);
        nodeB.addBranch(2, nodeD);

        nodeC.addBranch(1, nodeD);
        nodeC.addBranch(5, nodeE);

        nodeD.addBranch(8, nodeE);

        nodeE.addBranch(5, nodeI);
        nodeE.addBranch(5, target);

        nodeF.addBranch(1, nodeG);
        nodeF.addBranch(7, nodeH);

        nodeG.addBranch(3, nodeI);

        nodeH.addBranch(2, nodeI);

        nodeI.addBranch(3, target);

        final Node res = aStar(startA, target);
        printPath(res);
    }
}
