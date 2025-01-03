/*
 * Copyright 2017-2020 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.etcd.config;

import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.ClientBuilder;
import io.grpc.ClientInterceptor;
import io.grpc.Metadata.Key;
import io.grpc.netty.GrpcSslContexts;
import io.micronaut.context.annotation.Parameter;
import io.netty.handler.ssl.SslContext;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * Base class for etcd to be configured.
 */
public abstract class EtcdFactoryConfig {
  private String[] endpoints;
  private String user;
  private String password;
  private ExecutorService executorService;
  private String loadBalancerPolicy;
  private SslContext sslContext;
  private String authority;
  private Integer maxInboundMessageSize;
  private Map<Key<?>, Object> headers;
  private List<ClientInterceptor> interceptors;
  private ByteSequence namespace = ByteSequence.EMPTY;
  private long retryDelay = 500;
  private long retryMaxDelay = 2500;
  private Duration keepaliveTime = Duration.ofSeconds(30);
  private Duration keepaliveTimeout = Duration.ofSeconds(10);
  private Boolean keepaliveWithoutCalls = true;
  private ChronoUnit retryChronoUnit = ChronoUnit.MILLIS;
  private Duration retryMaxDuration;
  private Duration connectTimeout;
  private boolean waitForReady = true;

  /**
   * Constructor.
   * @param endpoints etcd server endpoints, at least one
   */
  public EtcdFactoryConfig(@Parameter String... endpoints) {
    this.endpoints = endpoints;
  }

  /**
   * Returns the comma-separated endpoints of the etcd cluster.
   * @return {@link String}
   */
  public String[] getEndpoints() {
    return endpoints;
  }

  /**
   * Sets the comma-separated endpoints of the etcd cluster.
   * @param endpoints etcd server endpoints, at least one
   */
  public void setEndpoints(String... endpoints) {
    this.endpoints = endpoints;
  }

  /**
   * @return The user to connect etcd server
   */
  public String getUser() {
    return user;
  }

  /**
   * @param user Set the user to connect the etcd server
   */
  public void setUser(String user) {
    this.user = user;
  }

  /**
   * @return the password to connect etcd server
   */
  public String getPassword() {
    return password;
  }

  /**
   * @param password sets the password to connect etcd server
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * @return get executor service
   */
  public ExecutorService getExecutorService() {
    return executorService;
  }

  /**
   *
   * @param executorService the executor service
   */
  public void setExecutorService(ExecutorService executorService) {
    this.executorService = executorService;
  }

  /**
   * @return etcd load balancer policy
   */
  public String getLoadBalancerPolicy() {
    return loadBalancerPolicy;
  }

  /**
   * @param loadBalancerPolicy config load balancer policy
   */
  public void setLoadBalancerPolicy(String loadBalancerPolicy) {
    this.loadBalancerPolicy = loadBalancerPolicy;
  }

  /**
   * @return the ssl context
   */
  public SslContext getSslContext() {
    return sslContext;
  }

  /**
   * SL/TLS context to use instead of the system default. It must have been configured with {@link
   * GrpcSslContexts}, but options could have been overridden.
   * @param sslContext config the ssl context
   */
  public void setSslContext(SslContext sslContext) {
    this.sslContext = sslContext;
  }

  /**
   * @return The authority used to authenticate connections to servers.
   */
  public String getAuthority() {
    return authority;
  }

  /**
   * @param authority config the authority used to authenticate connections to servers.
   */
  public void setAuthority(String authority) {
    this.authority = authority;
  }

  /**
   * @return the maximum message size allowed for a single gRPC frame.
   */
  public Integer getMaxInboundMessageSize() {
    return maxInboundMessageSize;
  }

  /**
   * @param maxInboundMessageSize Sets the maximum message size allowed for a single gRPC frame.
   */
  public void setMaxInboundMessageSize(Integer maxInboundMessageSize) {
    this.maxInboundMessageSize = maxInboundMessageSize;
  }

  /**
   * @return the headers to be added to http request headers
   */
  public Map<Key<?>, Object> getHeaders() {
    return headers;
  }

  /**
   * @param headers Sets headers to be added to http request headers.
   */
  public void setHeaders(Map<Key<?>, Object> headers) {
    this.headers = headers;
  }

  /**
   * @return the interceptors
   */
  public List<ClientInterceptor> getInterceptors() {
    return interceptors;
  }

  /**
   * @param interceptors config the interceptors
   */
  public void setInterceptors(List<ClientInterceptor> interceptors) {
    this.interceptors = interceptors;
  }

  /**
   * @return the namespace of each key used
   */
  public ByteSequence getNamespace() {
    return namespace;
  }

  /**
   * config the namespace of keys used in {@code KV}, {@code Txn}, {@code Lock} and {@code Watch}.
   * will be treated as no namespace.
   * @param namespace the namespace of each key used
   */
  public void setNamespace(ByteSequence namespace) {
    this.namespace = namespace;
  }

  /**
   * @return The delay between retries.
   */
  public long getRetryDelay() {
    return retryDelay;
  }

  /**
   * @param retryDelay config the delay between retries.
   */
  public void setRetryDelay(long retryDelay) {
    this.retryDelay = retryDelay;
  }

  /**
   * @return the max backing off delay between retries
   */
  public long getRetryMaxDelay() {
    return retryMaxDelay;
  }

  /**
   * @param retryMaxDelay config the max backing off delay between retries
   */
  public void setRetryMaxDelay(long retryMaxDelay) {
    this.retryMaxDelay = retryMaxDelay;
  }

  /**
   * @return The interval for gRPC keepalives
   */
  public Duration getKeepaliveTime() {
    return keepaliveTime;
  }

  /**
   * The interval for gRPC keepalives.
   * The current minimum allowed by gRPC is 10s
   *
   * @param keepaliveTime duration between keepalives
   */
  public void setKeepaliveTime(Duration keepaliveTime) {
    this.keepaliveTime = keepaliveTime;
  }

  /**
   * @return The timeout for gRPC keepalives
   */
  public Duration getKeepaliveTimeout() {
    return keepaliveTimeout;
  }

  /**
   * The timeout for gRPC keepalives.
   * @param keepaliveTimeout The gRPC keep alive timeout duration.
   */
  public void setKeepaliveTimeout(Duration keepaliveTimeout) {
    this.keepaliveTimeout = keepaliveTimeout;
  }

  /**
   * @return Keepalive option for gRPC
   */
  public Boolean getKeepaliveWithoutCalls() {
    return keepaliveWithoutCalls;
  }

  /**
   * Keepalive option for gRPC.
   * @param keepaliveWithoutCalls the gRPC keep alive without calls.
   */
  public void setKeepaliveWithoutCalls(Boolean keepaliveWithoutCalls) {
    this.keepaliveWithoutCalls = keepaliveWithoutCalls;
  }

  /**
   * @return The retries period unit.
   */
  public ChronoUnit getRetryChronoUnit() {
    return retryChronoUnit;
  }

  /**
   * @param retryChronoUnit config the retries period unit.
   */
  public void setRetryChronoUnit(ChronoUnit retryChronoUnit) {
    this.retryChronoUnit = retryChronoUnit;
  }

  /**
   * @return the retries max duration.
   */
  public Duration getRetryMaxDuration() {
    return retryMaxDuration;
  }

  /**
   * @param retryMaxDuration the retries max duration.
   */
  public void setRetryMaxDuration(Duration retryMaxDuration) {
    this.retryMaxDuration = retryMaxDuration;
  }

  /**
   * @return the connect timeout duration.
   */
  public Duration getConnectTimeout() {
    return connectTimeout;
  }

  /**
   * Sets the connection timeout in milliseconds.
   * Clients connecting to fault tolerant etcd clusters (eg, clusters with >= 3 etcd server
   * peers/endpoints) should consider a value that will allow switching timely from a
   * crashed/partitioned peer to a consensus peer.
   * @param connectTimeout config the connect timeout duration.
   */
  public void setConnectTimeout(Duration connectTimeout) {
    this.connectTimeout = connectTimeout;
  }

  /**
   * Enable gRPC's wait for ready semantics.
   *
   * @return if this client uses gRPC's wait for ready semantics.
   */
  public boolean waitForReady() {
    return waitForReady;
  }

  /**
   * Configure the gRPC's wait for ready semantics.
   *
   * @param  waitForReady if this client should use gRPC's wait for ready semantics. Enabled by default.
   */
  public void waitForReady(boolean waitForReady) {
    this.waitForReady = waitForReady;
  }
}
