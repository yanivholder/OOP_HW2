package OOP2.Solution;
import OOP2.Provided.FaceOOP;
import OOP2.Provided.*;

import java.util.*;
import java.util.HashSet;

public class FaceOOPImpl implements FaceOOP, Iterable<Person> {

//    public static class Graph{
//        class Node{
//            Person person;
//            boolean visited;
//            Integer dist;
//
//            public Node(Person person){
//                person = person;
//                visited = false;
//                dist = 0;
//            }
//        }
//        private Map<Node, List<Node> > map = new HashMap<>();
//        private Set<Node> nodes = new HashSet<>();
//
//        public void addVertex(Node s)
//        {
//            map.put(s, new LinkedList<Node>());
//        }
//
//        public void addEdge(Node source, Node destination)
//        {
//
//            if (!map.containsKey(source)){
//                addVertex(source);
//            }
//            if (!map.containsKey(destination)){
//                addVertex(destination);
//            }
//            map.get(source).add(destination);
//            map.get(destination).add(source);
//        }



//        private static void addEdge(ArrayList<ArrayList<Node>> adj, Node i, Node j) {
//            adj.get(i).add(j);
//            adj.get(j).add(i);
//        }
//        private int shortestDistanceWrapper(Node s, Node dest, int v){
//
//            return shortestDistance(???, s, dest, v);
//        }
//        private int shortestDistance(ArrayList<ArrayList<Node>> adj, Node s, Node dest, int v) throws ConnectionDoesNotExistException{
//            // predecessor[i] array stores predecessor of
//            // i and distance array stores distance of i
//            // from s
//            Node pred[] = new Node[v];
//            Node dist[] = new Node[v];
//
//            if (BFS(adj, s, dest, v, pred, dist) == false) {
//                throw new ConnectionDoesNotExistException();
//            }
//
//            // LinkedList to store path
//            LinkedList<Integer> path = new LinkedList<Integer>();
//            Node crawl = dest;
//            path.add(crawl);
//            while (pred[crawl] != -1) {
//                path.add(pred[crawl]);
//                crawl = pred[crawl];
//            }
//
//            return dist[dest];
//        }
//        private static boolean BFS(ArrayList<ArrayList<Integer>> adj, int src, int dest, int v, int pred[], int dist[]){
//            LinkedList<Node> queue = new LinkedList<>();
//            boolean visited[] = new boolean[v];
//            for (int i = 0; i < v; i++) {
//                visited[i] = false;
//                dist[i] = Integer.MAX_VALUE;
//                pred[i] = -1;
//            }
//
//            visited[src] = true;
//            dist[src] = 0;
//            queue.add(src);
//
//            while (!queue.isEmpty()) {
//                int u = queue.remove();
//                for (int i = 0; i < adj.get(u).size(); i++) {
//                    if (visited[adj.get(u).get(i)] == false) {
//                        visited[adj.get(u).get(i)] = true;
//                        dist[adj.get(u).get(i)] = dist[u] + 1;
//                        pred[adj.get(u).get(i)] = u;
//                        queue.add(adj.get(u).get(i));
//
//                        // stopping condition (when we find
//                        // our destination)
//                        if (adj.get(u).get(i) == dest)
//                            return true;
//                    }
//                }
//            }
//            return false;
//        }
//        public Graph(List<Person> ppl, ){
//            Iterator<Person> it = ppl.iterator();
//            while (it.hasNext()) {
//                Person temp = it.next();
//                Iterator<Person> edge_it = temp.getFriends().iterator();
//                while (edge_it.hasNext()) {
//                    this.addEdge(new Node(temp), new Node(edge_it.next()));
//                }
//            }
//        }
//
//
//
//
//    }
    public static class StatusIteratorImpl implements StatusIterator {

        private Iterator<Status> it;

        StatusIteratorImpl(Iterator<Status> it) {
            this.it = it;
        }
        public boolean hasNext() {
            return it.hasNext();
        }
        public Status next() {
            return it.next();
        }
        public void remove() { }
    }
    public static class ComparePersonsById implements Comparator<Person> {
        @Override
        public int compare(Person p1, Person p2) {
            return p1.getId() - p2.getId();
        }
    }
    public static class CompareStatusByRecent implements Comparator<Status> {
        @Override
        public int compare(Status s1, Status s2) {
            int persons_id_diff = s1.getPublisher().getId() - s2.getPublisher().getId();
            if(persons_id_diff != 0) {
                return persons_id_diff;
            }
            else {
                return s2.getId() - s1.getId();
            }
        }
    }
    public static class CompareStatusByPopular implements Comparator<Status> {
        @Override
        public int compare(Status s1, Status s2) {
            int persons_id_diff = s1.getPublisher().getId() - s2.getPublisher().getId();
            if(persons_id_diff != 0) {
                return persons_id_diff;
            }
            else {
                return s2.getLikesCount() - s1.getLikesCount();
            }
        }
    }

	private List<Person> users = new ArrayList<>();

	public FaceOOPImpl()
	{}

	@Override
	public Person joinFaceOOP(Integer id, String name) throws PersonAlreadyInSystemException {
		Person new_user = new PersonImpl(id, name);
		if (users.contains(new_user)) {
			throw new PersonAlreadyInSystemException();
		}
		else {
			users.add(new_user);
		}
        return new_user;
	}

	@Override
	public int size() {
		return users.size();
	}
	@Override
	public Person getUser(Integer id) throws PersonNotInSystemException {
        for (Person p : users) {
			if (p.getId().equals(id)){
				return p;
			}
		}
		throw new PersonNotInSystemException();
	}
	@Override
    public void addFriendship(Person p1, Person p2)
            throws PersonNotInSystemException, SamePersonException,
            ConnectionAlreadyExistException {
		if (!users.contains(p1) || !users.contains(p2)){
			throw new PersonNotInSystemException();
		}
		else {
			p1.addFriend(p2);
			p2.addFriend(p1);
		}
	}
    @Override
    public Iterator<Person> iterator() {
        // TODO: earse the comment
//        this.users.sort(new ComparePersonsById());
        return this.users.iterator();
    }
    // A function to create a collection with all of p's friends statuses
    private List<Status> CreateFriendStatusesCollection(Person p) {
        List<Status> status_list = new ArrayList<>();
        for(Person tmp_p: p.getFriends()) {
            for(Status s: tmp_p.getStatusesRecent()) {
                status_list.add(s);
            }
        }
        return status_list;
    }
    @Override
    public StatusIterator getFeedByRecent(Person p) throws PersonNotInSystemException {
        if(!this.users.contains(p))
            throw new PersonNotInSystemException();
        List<Status> p_friends_statuses = CreateFriendStatusesCollection(p);
        // TODO: earse the comment
//        p_friends_statuses.sort(new ComparePersonsByRecent());
        return new StatusIteratorImpl(p_friends_statuses.iterator());
    }
    @Override
    public StatusIterator getFeedByPopular(Person p) throws PersonNotInSystemException {
        if(!this.users.contains(p))
            throw new PersonNotInSystemException();
        List<Status> p_friends_statuses = CreateFriendStatusesCollection(p);
        // TODO: earse the comment
//        p_friends_statuses.sort(new ComparePersonsByPopular());
        return new StatusIteratorImpl(p_friends_statuses.iterator());
    }
    public Integer rank(Person source, Person target) throws PersonNotInSystemException, ConnectionDoesNotExistException {

        if(!this.users.contains(source) || !this.users.contains(target))
            throw new PersonNotInSystemException();

        Queue<Person> q = new LinkedList<>();
        Hashtable<Integer, Boolean> visited = new Hashtable<>();
        Hashtable<Integer, Integer> d = new Hashtable<>();

        visited.put(source.getId(), Boolean.TRUE);
        d.put(source.getId(), 0);

        for (Person temp : source.getFriends()) {
            d.put(temp.getId(), 1);
            visited.put(temp.getId(), Boolean.TRUE);
            q.add(temp);
        }

        while (!q.isEmpty()) {
            Person u = q.poll();

            for (Person temp : u.getFriends()) {
                if (!visited.containsKey(temp)){
                    visited.put(temp.getId(), Boolean.TRUE);
                    d.put(temp.getId(), d.get(u) + 1);
                    q.add(temp);
                }
            }
        }
        return d.get(target);
    }






}
