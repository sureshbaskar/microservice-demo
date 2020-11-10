https://www.replex.io/blog/the-ultimate-guide-to-the-kubernetes-dashboard-how-to-install-and-integrate-metrics-server

Metrics-server replaces Heapster as the primary cluster-wide metrics aggregator for Kubernetes with an integration into the Kubernetes dashboard.
In the past, there was no Resource Metrics API and a service called Heapster, now deprecated, used to gather all the cAdvisor metrics and bit more manually. Around the 1.6 to 1.8 Kubernetes releases the Resource Metrics API was added. In concert, Heapster was removed and Metrics Server is now the de facto service that aggregates metrics from the Metrics API.

kubectl apply -f deploy/kubernetes/



helm install metrics-server stable/metrics-server \
--namespace kube-system \
--set args[0]="--kubelet-preferred-address-types=InternalIP" \
--set args[1]="--kubelet-insecure-tls"

kubectl proxy &
curl localhost:8001/api/v1/nodes/$NODE/proxy/stats/
curl localhost:8001/api/v1/nodes/$(kubectl get nodes -o=jsonpath="{.items[0].metadata.name}")/proxy/metrics
curl localhost:8001/metrics
