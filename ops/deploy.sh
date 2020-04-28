j2 ./ops/Kubernetes/deployment-defination.j2.yml > ./ops/Kubernetes/deployment-defination.yml
kubectl create -f ops/kubernetes/deployment-definition.yml --record
kubectl create -f ops/kubernetes/service-definition.yml