# Extract from: https://github.com/ClayFlannigan/icp/blob/master/icp.py
import numpy as np
import math

def best_fit_transform(A, B):
    '''
    Calculates the least-squares best-fit transform that maps corresponding points A to B in m spatial dimensions
    Input:
      A: Nxm numpy array of corresponding points
      B: Nxm numpy array of corresponding points
    Returns:
      T: (m+1)x(m+1) homogeneous transformation matrix that maps A on to B
      R: mxm rotation matrix
      t: mx1 translation vector
    '''

    assert A.shape == B.shape

    # get number of dimensions
    m = A.shape[1]

    # translate points to their centroids
    centroid_A = np.mean(A, axis=0)
    centroid_B = np.mean(B, axis=0)
    AA = A - centroid_A
    BB = B - centroid_B

    print(f"Matrix AA:\n{AA}")
    print(f"Matrix BB:\n{BB}")

    # rotation matrix
    H = np.dot(AA.T, BB)
    U, S, Vt = np.linalg.svd(H)
    R = np.dot(Vt.T, U.T)

    # special reflection case
    if np.linalg.det(R) < 0:
        Vt[m - 1, :] *= -1
        R = np.dot(Vt.T, U.T)

    # translation
    t = centroid_B.T - np.dot(R, centroid_A.T)

    # homogeneous transformation
    T = np.identity(m + 1)
    T[:m, :m] = R
    T[:m, m] = t

    return T, R, t


if __name__ == "__main__":
    # these values are taken by creating a custom RealMatrixFormat in commons math, then printing it as observed
    # at the point (-41.74, 0.23) on the field

    observed = np.array([[41.540890252, 38.7375068515], [51.6717175001, 38.9374319979], [61.9075110194, 38.684106289],
                         [71.960228818, 38.2619323671], [82.3833778416, 38.4159999922], [91.5902329185, 38.8777472847],
                         [41.7, 0], [41.5773089379, 33.6686409212], [42.0435965148, -34.0462331558],
                         [94.3695591546, 6.5989624162], [94.3695591546, -6.5989624162], [5.7964667967, -0.2024170809],
                         [78.2058355378, 19.4989047853], [77.5, 0], [77.8536215989, -20.8608150353], [99.5, 0],
                         [94.439108389, 34.3730244042], [98.9549285891, 10.4005820951], [99.6351591199, 19.3671130307],
                         [98.9775422422, 30.2604714388], [94.439108389, -34.3730244042],
                         [98.9549285891, -10.4005820951], [99.2819814745, -21.103036618],
                         [98.9775422422, -30.2604714388], [41.9797024729, -39.1467058676],
                         [51.6717175001, -38.9374319979], [61.9075110194, -38.684106289],
                         [71.2815061319, -39.5119840501], [81.7003788086, -39.8479372431],
                         [91.5902329185, -38.8777472847]])
    correlated = np.array(
        [[0, 39], [10, 39], [20, 39], [30, 39], [40, 39], [50, 39], [0, 0], [0, 34], [0, -34], [52.5, 9.16],
         [52.5, -9.16], [-36, 0], [36, 20.16], [36, 0], [36, -20.16], [57.5, 0], [52.5, 34], [57.5, 10], [57.5, 20],
         [57.5, 30], [52.5, -34], [57.5, -10], [57.5, -20], [57.5, -30], [0, -39], [10, -39], [20, -39], [30, -39],
         [40, -39], [50, -39]])

    T, R, t = best_fit_transform(observed, correlated)
    print("Rotation matrix:")
    print(R)
    print("Transform:")
    print(t)
    angle_degrees = math.degrees(math.acos(R[0, 0]))
    angle_degrees_360 = (angle_degrees + 360) % 360
    print(f"Angle degrees: {angle_degrees_360}")
