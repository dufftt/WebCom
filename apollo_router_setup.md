—command to create supergraph
APOLLO_ELV2_LICENSE=accept rover supergraph compose --config supergraph.yaml > supergraph.graphql

—command to create router for apollo
docker run -it --rm \
  -p 4000:4000 \
  -v $(pwd)/supergraph.graphql:/dist/schema/supergraph.graphql \
  -e APOLLO_ELV2_LICENSE=accept \
  -e APOLLO_ROUTER_LISTEN_ADDR=0.0.0.0:4000 \
  ghcr.io/apollographql/router:v2.12.1 \
  --supergraph /dist/schema/supergraph.graphql \
  --dev

—command to run router locally
./router --supergraph supergraph.graphql --config router.yaml



Map your service to a Docker network
If your subgraph runs in another Docker container, connect both containers to the same Docker network:

docker network create apollo-net

# run subgraph
docker run -d --name subgraph --network apollo-net -p 5000:5000 subgraph-image

# run router
docker run -d --name apollo-router --network apollo-net -p 4000:4000 \
  -v $(pwd)/supergraph.graphql:/dist/schema/supergraph.graphql \
  -e APOLLO_ELV2_LICENSE=accept \
  ghcr.io/apollographql/router:v2.12.1 \
  --supergraph /dist/schema/supergraph.graphql



Exactly—that’s the right way to think about it. Let me break it down clearly so it sticks:

1. Local development (on your Mac)
	•	You can run ./router locally.
	•	Your supergraph URLs can point to localhost for your subgraphs, because everything is on your host machine.
	•	Introspection works perfectly this way.
Example:

subgraphs:
  accounts:
    url: http://localhost:5001/graphql
  products:
    url: http://localhost:5002/graphql


2. Dockerized deployment (all containers)
	•	When you containerize your services and the router, localhost inside a container only refers to that container.
	•	So the supergraph should use container DNS names or Docker network aliases.
Example Docker network approach:

docker network create apollo-net

docker run -d --name accounts --network apollo-net my-accounts-image
docker run -d --name products --network apollo-net my-products-image
docker run -d --name apollo-router --network apollo-net -p 4000:4000 \
  -v $(pwd)/supergraph.graphql:/dist/schema/supergraph.graphql \
  -e APOLLO_ELV2_LICENSE=accept \
  ghcr.io/apollographql/router:v2.12.1 \
  --supergraph /dist/schema/supergraph.graphql

supergraph for containers:

subgraphs:
  accounts:
    url: http://accounts:5001/graphql
  products:
    url: http://products:5002/graphql


✅ Key takeaway:
	•	Local development: use localhost for subgraph URLs → run ./router.
	•	Docker deployment: use Docker container names (or host.docker.internal for Mac host access) → run docker run router with proper network.
This pattern is actually pretty standard for Apollo federated setups—you basically just swap the subgraph URLs depending on environment.

