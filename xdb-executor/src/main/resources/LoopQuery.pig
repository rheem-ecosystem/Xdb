IMPORT 'file:///kmeans/udfs.class' AS kmeans;
lines = LOAD 'file://myData.txt';
points = MAP lines -> kmeans.parsePoints();
centroids = LOAD 'file://myData.txt'; /**kmeans.createRandomCentroids(2);*/
final_centroids = REPEAT centroids LIKE current_centroid FOR 50 {
	distance = MAP points -> kmeans.selectNearestCentroid() WITH BROADCAST current_centroid;
	centroids_sum = REDUCE distance -> kmeans.reduce();
	new_centroids = MAP centroids_sum -> kmeans.average();
}
STORE final_centroids 'file:///output/kmeans';