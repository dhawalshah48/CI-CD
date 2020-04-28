SERVICE_PRESENT="$(kubectl describe services | grep -c "go-pipeline-service")"
j2 ./ops/Kubernetes/deployment-defination.j2.yml > ./ops/Kubernetes/deployment-defination.yml
if [ "$SERVICE_PRESENT" == 0 ]
then
  kubectl create -f ops/kubernetes/deployment-definition.yml --record
  kubectl create -f ops/kubernetes/service-definition.yml
else
  kubectl apply -f ops/kubernetes/deployment-definition.yml --record
fi 