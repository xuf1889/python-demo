# K_means cluster
print('         ')
print('  ----K_means cluster----')

import matplotlib.pyplot as plt 
import numpy as np 
from sklearn import datasets
from sklearn import metrics 
from sklearn.cluster import KMeans 

# Load the diabetes dataset 
diabetes = datasets.load_diabetes()

# Use only one feature 
diabetes_X = diabetes.data[:, np.newaxis, 2]*2000
diabetes_y = diabetes.target-100
X = np.random.randn(30, 2)

# 3 cluster
# Create kmeans object 
est = KMeans(n_clusters=3)
# Train the model using the training sets 
est.fit(X)
# The coefficients 
labels = est.labels_
met=metrics.silhouette_score(X,labels,metric='euclidean')
centers = est.cluster_centers_

# Plot data sets 
plt.scatter(X[:,0],X[:,1], c=labels, cmap=plt.cm.Paired) #training sets
plt.scatter(centers[:, 0], centers[:, 1], s=60, facecolors='black', label='cluste_centers') 
plt.legend() 
plt.title("k_means cluster ( k = 3 , score = %.3f )" % (met) )
plt.show()
print('         fig.1 3聚类')

# 5 cluster
# Create kmeans object 
est = KMeans(n_clusters=5)
# Train the model using the training sets 
est.fit(X)
# The coefficients 
labels = est.labels_
met=metrics.silhouette_score(X,labels,metric='euclidean')
centers = est.cluster_centers_


# Plot data sets 
plt.scatter(X[:,0],X[:,1], c=labels, cmap=plt.cm.Paired) #training sets
plt.scatter(centers[:, 0], centers[:, 1], s=60, facecolors='black', label='cluste_centers') 
plt.legend() 
plt.title("k_means cluster ( k = 5 , score = %.3f )" % (met))
plt.show()
print('         fig.2 5聚类')

# 3 cluster
# Create kmeans object 
est = KMeans(n_clusters=8)
# Train the model using the training sets 
est.fit(X)
# The coefficients 
labels = est.labels_
met=metrics.silhouette_score(X,labels,metric='euclidean')
centers = est.cluster_centers_

# Plot data sets 
plt.scatter(X[:,0],X[:,1], c=labels, cmap=plt.cm.Paired) #training sets
plt.scatter(centers[:, 0], centers[:, 1], s=60, facecolors='black', label='cluste_centers') 
plt.legend() 
plt.title("k_means cluster ( k = , score = %.3f )" % (met) )
plt.show()
print('         fig.3 8聚类')
